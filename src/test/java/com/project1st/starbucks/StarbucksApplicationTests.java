package com.project1st.starbucks;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project1st.starbucks.basket.repository.ShoppingBasketOptionRepository;
import com.project1st.starbucks.basket.repository.ShoppingBasketRepository;

@SpringBootTest
class StarbucksApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired ShoppingBasketRepository sbRepo;
	@Test
	void testFindAll() {
		System.out.println(sbRepo.findAll());		
	}

	@Autowired ShoppingBasketOptionRepository sbopRepo;
	@Test
	void testFindop() {
		System.out.println(sbopRepo.findAll());		
	}
}
