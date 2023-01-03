package com.parkmawani.starbucksproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.parkmawani.starbucksproject.repository.MemberRepository;

@SpringBootTest
class StarbucksprojectApplicationTests {

	
	@Autowired MemberRepository Repo;
	@Test
	void memberFindAll() {
		System.out.println(Repo.findAll());
	}

}
