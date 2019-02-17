package com.example.demo;

import com.example.demo.model.AyMood;
import com.example.demo.model.AyUser;
import com.example.demo.mq.AyMoodProducer;
import com.example.demo.service.AyMoodService;
import com.example.demo.service.AyUserService;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
//参数化运行器
@SpringBootTest
//测试SpringApplication，因为springboot的程序入口是SpringApplication
public class DemoApplicationTests {

	@Test
	//单元测试注解
	public void contextLoads() {


	}

	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 * mysql集成spring boot 简单测试
	 */

    @Test
	public void  mysqlTest(){
        String  sql ="SELECT id ,name ,password from ay_user";
		List<AyUser>  userlist = (List<AyUser>) jdbcTemplate.query(sql, new RowMapper<AyUser>(){

             @Override
			public AyUser mapRow (ResultSet rs,int rowNum)throws SQLException{
				 AyUser  user=new AyUser();
				 user.setId(rs.getString("id"));
				 user.setName(rs.getString("name"));
				 user.setPassword(rs.getString("password"));

				 return  user;
			 }
		});
     System.out.println("查询成功");

       for (AyUser user:userlist){
		   System.out.println("【id】"+user.getId()+"【name】"+user.getName()+"【password】"+ user.getPassword());

	   }
     
	}


	/**
	 * 自定义的方法
	 */


   @Test
   public void testRepository(){
     //查询所有数据
	   List<AyUser> userList= ayUserService.findAll();
	   System.out.println("【查询所有数据】"+userList);

	   //通过name查询数据

	   List<AyUser> userList2 = ayUserService.findByName("阿毅");
	   System.out.println(" 【通过name查询数据】" + userList2.size()+userList2.toString());
	   Assert.isTrue(userList2.get(0).getName().equals("阿毅"),"data error!");



   }

	/**
	 * 测试springboot事物支持
	 */
    @Test
    public void testTransactional(){

		AyUser ayUser =new AyUser();
		ayUser.setId("9");
		ayUser.setName("好好奋斗");
		ayUser.setPassword("123");

		ayUserService.save(ayUser);

	}
    Logger logger = LogManager.getLogger(this.getClass());


    @Test
    public void testLog4j(){
        ayUserService.delete("4");
        logger.info("delete success!!!");
    }


	/**
	 * 测试mybatis
	 */
	@Resource
	private AyUserService ayUserService;
    @Test
	public void  testmybnstis(){

		AyUser ayUser=	ayUserService.findByNameAndPassword("贺雷","1111");
		logger.info(ayUser.getId()+"信息"+ayUser.getName());

	}

	/**
	 * mq
	 */

	@Resource
	private AyMoodService ayMoodService;


	@Test
	public void testAyMood(){
		AyMood ayMood = new AyMood();
		ayMood.setId("1");
		//用户阿毅id为1
		ayMood.setUserId("1");
		ayMood.setPraiseNum(0);
		//说说内容
		ayMood.setContent("这是我的第一条微信说说！！！");
		ayMood.setPublishTime(new Date());
		//往数据库保存一条数据，代码用户阿毅发表了一条说说
		AyMood mood = ayMoodService.save(ayMood);
	}

	/**
	 * 用户发表说说的类虽然都开发完成了，
	 * 但是有一个问题，我们都知道微信的用户量极大，
	 * 每天都有几亿的用户发表不同的说说，
	 * 如果按照我们上面的做法，用户每发一条说说，
	 * 后端都会单独开一个线程，
	 * 将该说说的内容实时的保存到数据库中。
	 * 我们都知道后端服务系统的线程数和数据库线程池中的线程数量都是固定而且宝贵的，
	 * 因此将用户发表的说说实时保存到数据库中，必然造成后端服务和数据库极大的压力。
	 * 所有我们使用ActiveMQ做异步消费，来抵抗用户大并发发表说说而产生的压力，提高系统整体的性能。
	 */

	@Resource
	private AyMoodProducer ayMoodProducer;


	@Test
	public void testActiveMQ() {

		Destination destination = new ActiveMQQueue("ay.queue");
		ayMoodProducer.sendMessage(destination, "hello,mq!!!");
	}


	/**
	 * 用户发表说说，异步保存
	 */

	@Test
	public void testActiveMQAsynSave() {
		AyMood ayMood = new AyMood();
		ayMood.setId("2");
		ayMood.setUserId("2");
		ayMood.setPraiseNum(0);
		ayMood.setContent("这是我的第一条微信说说！！！");
		ayMood.setPublishTime(new Date());
		String msg = ayMoodService.asynSave(ayMood);
		System.out.println("异步发表说说 :" + msg);

	}

	/**
	 * testAsync
	 */

    @Test
	public void testAsync(){


		long startTime=System.currentTimeMillis();
		System.out.println("第一次查询所有用户");
		List<AyUser>  ayUserList=  ayUserService.findAll();
		System.out.println("第二次查询所有用户");
		List<AyUser>  ayUserList2=  ayUserService.findAll();
		System.out.println("第三次查询所有用户");
		List<AyUser>  ayUserList3=  ayUserService.findAll();
		long endTime=System.currentTimeMillis();
		System.out.println("总共消耗："+(endTime-startTime)+"毫秒");

	}


	/**
	 * 异步测试
	 */


	@Test
	public void testAsync2()throws Exception{

		long startTime=System.currentTimeMillis();
		System.out.println("第一次查询所有用户");
		Future<List<AyUser>> ayUserList=  ayUserService.findAsynAll();
		System.out.println("第二次查询所有用户");
		Future<List<AyUser>> ayUserList2=  ayUserService.findAsynAll();
		System.out.println("第三次查询所有用户");
		Future<List<AyUser>> ayUserList3=  ayUserService.findAsynAll();
		 while (true){
		 	if(ayUserList.isDone() && ayUserList2.isDone() && ayUserList3.isDone()){
		 		break;
			}
			else {
		 		Thread.sleep(10);
			}
		 }

		long endTime=System.currentTimeMillis();
		System.out.println("总共消："+(endTime-startTime)+"毫秒");

	}

}

