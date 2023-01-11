package com.project1st.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project1st.order.menu.repository.MenuBasicInfoRepository;
import com.project1st.order.store.repository.StoreBasicInfoRepository;
import com.project1st.order.store.service.StoreService;

@SpringBootTest
class OrderApplicationTests {
	@Autowired MenuBasicInfoRepository mRepo;
	@Autowired StoreBasicInfoRepository sRepo;
	@Autowired StoreService stService;

	
	@Test
	void showMenuList() { // 메뉴 전체보기
		System.out.println(mRepo.findAll());
	}

	@Test
	void showMenuDetailList() { // 특정메뉴 상세보기
		System.out.println(mRepo.findById(1L));
	}
	
	// @Test
	// void showStoreMenuList() {
	// 	System.out.println(stService.storeMenuList(true, 1));
	// }
}
