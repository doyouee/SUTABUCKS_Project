package com.project1st.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.starbucks.order.menu.repository.MenuBasicInfoRepository;
import com.starbucks.order.store.repository.StoreBasicInfoRepository;
import com.starbucks.order.store.service.StoreService;

@SpringBootTest
class OrderApplicationTests {
	@Autowired MenuBasicInfoRepository mRepo;
	@Autowired StoreBasicInfoRepository sRepo;
	@Autowired StoreService stService;

	@Test
	void showMenuList() {
		System.out.println(mRepo.findAll());
	}

	@Test
	void showMenuDetailList() {
		System.out.println(mRepo.findByMbiSeq(1));
	}
	
	// @Test
	// void showStoreMenuList() {
	// 	System.out.println(stService.storeMenuList(true, 1));
	// }
}
