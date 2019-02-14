package com.example.demo.repository;

import com.example.demo.model.AyMood;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 说说repository接口
 */
public interface AyMoodRepository extends JpaRepository<AyMood,String> {



}
