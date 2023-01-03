package com.parkmawani.starbucksproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.parkmawani.starbucksproject.repository.AnnouncementRepository;
import com.parkmawani.starbucksproject.repository.EventRepository;
import com.parkmawani.starbucksproject.repository.MemberRepository;

@SpringBootTest
class StarbucksprojectApplicationTests {
	@Autowired MemberRepository mRepo;
	@Autowired EventRepository eRepo;
	@Autowired AnnouncementRepository aRepo;
	@Test
	void eventFindAll() {
		System.out.println(eRepo.findAll());
	}

	@Test
	void memberFindAll() {
		System.out.println(mRepo.findAll());
	}
	
	@Test
	void announceFindAll() {
		System.out.println(aRepo.findAll());
	}

}
