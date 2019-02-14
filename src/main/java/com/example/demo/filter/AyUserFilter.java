package com.example.demo.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 过滤器 filter
 */

/**
 * @WebFilter：用于将一个类声明为过滤器，该注解将会在应用部署时被容器处理，
 * 容器根据具体的属性配置将相应的类部署为过滤器。这样我们在Web应用中使用监听器时，
 * 不需要在web.xml文件中配置监听器的相关描述信息了。该注解的常用属性有：
 * filterName、urlPatterns、value等。filterName属性用于指定过滤器的name，
 * 等价于XML配置文件中的标签。urlPatterns属性用于指定一组过滤器的URL匹配模式，
 * 等价于XML配置文件中的标签。value属性等价于urlPatterns属性，但是两者不可以同时使用。
 *AyUserFilter.java类开发完成之后，我们需要在入口类MySpringBootApplication.
 * java中添加注解@ServletComponentScan，
 */
@WebFilter(filterName = "ayUserFilter", urlPatterns = "/*")
public class AyUserFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("---------->>> init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("---------->>> doFilter");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("---------->>> destory");
    }


}
