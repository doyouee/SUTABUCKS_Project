package com.project1st.starbucks.store.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.project1st.starbucks.menu.entity.MenuBasicInfoEntity;
import com.project1st.starbucks.menu.repository.MenuBasicInfoRepository;
import com.project1st.starbucks.menu.vo.MenuInfoVO;
import com.project1st.starbucks.store.entity.StoreBasicInfoEntity;
import com.project1st.starbucks.store.entity.StoreMenuConnectEntity;
import com.project1st.starbucks.store.repository.StoreBasicInfoRepository;
import com.project1st.starbucks.store.repository.StoreMenuConnectRepository;
import com.project1st.starbucks.store.vo.StoreInfoVO;
import com.project1st.starbucks.store.vo.StoreMenuAddVO;
import com.project1st.starbucks.store.vo.StoreMenuVO;



@Service
public class StoreService {
    @Autowired StoreBasicInfoRepository sRepo;
    @Autowired MenuBasicInfoRepository mRepo;
    @Autowired StoreMenuConnectRepository smRepo;

    

    // <가게에 등록된 메뉴 보여주기> // 점주 로그인 상태에서 본인의 가게에만 설정할 수 있도록
    public ResponseEntity<Object> storeMenuList(Pageable pageable, Long storeSeq) {
        /* OLD CODE
        // Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        // resultMap.put("list", smRepo.findAll());
        // List<StoreMenuConnectEntity> list = smRepo.findByStore(sRepo.findById(storeSeq).get(), pageable).getContent();
        // StoreInfoVO store = null;
        // List<MenuInfoVO> menuList = new ArrayList<MenuInfoVO>();
        // for(StoreMenuConnectEntity s : list) {
        //     store = new StoreInfoVO(s.getStore());
        //     menuList.add(new MenuInfoVO(s.getMenu()));
        // }
        // resultMap.put("list", new StoreMenuVO(store, menuList));
        // return new ResponseEntity<>(resultMap, HttpStatus.OK);
        */
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        
        // 존재하지 않는 가게 Seq
        Optional<StoreBasicInfoEntity> storeOptional =  sRepo.findById(storeSeq);
        if(storeOptional.isEmpty()){
            resultMap.put("message", storeSeq + "번 가게는 존재하지 않습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }

        // 가게 메뉴 보여주기        
        resultMap.put("list", smRepo.findAll());
        List<StoreMenuConnectEntity> list = smRepo.findByStore(sRepo.findById(storeSeq).get(), pageable).getContent();
        StoreInfoVO store = null;
        List<MenuInfoVO> menuList = new ArrayList<MenuInfoVO>();
        for(StoreMenuConnectEntity s : list) {
            store = new StoreInfoVO(s.getStore());
            menuList.add(new MenuInfoVO(s.getMenu()));
        }
        resultMap.put("list", new StoreMenuVO(store, menuList));

        //페이징 처리
        Page<StoreMenuConnectEntity> page = smRepo.findAll(pageable);
        resultMap.put("totalPage", page.getTotalPages());
        resultMap.put("MenuTotalCount", (page.getTotalElements()) -2);
        resultMap.put("currentPage", page.getNumber());

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
    


    // <특정 가게에 특정 메뉴 상세보기> // 점주 로그인 상태에서 본인의 가게에만 설정할 수 있도록
    public ResponseEntity<Object> storeMenuDetail(Long storeSeq, Long menuSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        // 메뉴 및 가게 seq가 기존 테이블에 존재하는지 찾기
        Optional<StoreBasicInfoEntity> storeOptional =  sRepo.findById(storeSeq);
        Optional<MenuBasicInfoEntity> menuOptional =  mRepo.findById(menuSeq);
        if(storeOptional.isEmpty()){
            resultMap.put("message", storeSeq + "번 가게는 존재하지 않습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        if(menuOptional.isEmpty()){
            resultMap.put("message", menuSeq + "번 메뉴는 존재하지 않습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }

        // 연결테이블에 존재하지 않는 seq
        StoreMenuConnectEntity smEntity = smRepo.findByStoreAndMenu(storeOptional.get(), menuOptional.get());
        if((smEntity == null) || (smEntity.getStore().getSbiSeq() != storeSeq) || (smEntity.getMenu().getMbiSeq() != menuSeq)){
            resultMap.put("message", storeSeq + "번 가게의 " + menuSeq + "번 메뉴는 존재하지 않습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }


        // 메뉴 상세보기
        resultMap.put("message", storeSeq + "번 가게의 " + menuSeq + "번 메뉴를 소개합니다.");
        resultMap.put("list", smRepo.findByStoreAndMenu(storeOptional.get(), menuOptional.get()));
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
    


     // <가게에 메뉴 등록하기> // 점주 로그인 상태에서 본인의 가게에만 설정할 수 있도록
    public ResponseEntity<Object> insertStoreMenuList(StoreMenuAddVO data) {
        /* OLD CODE
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        Optional<StoreBasicInfoEntity> store =  sRepo.findById(data.getStore());
        Optional<MenuBasicInfoEntity> menu =  mRepo.findById(data.getStore());  -> 이거 data.getMenu() 아닌가?

        if(!store.isPresent()) { System.out.println("존재하지 않는 가게입니다.");}
        if(!menu.isPresent()) { System.out.println("존재하지 않는 메뉴입니다.");}

        StoreMenuConnectEntity entity = StoreMenuConnectEntity.builder()
            .store(sRepo.findById(data.getStore()).get())
            .menu(mRepo.findById(data.getMenu()).get()).build();
        smRepo.save(entity);
        resultMap.put("result", true);
        resultMap.put("inserted_data", entity);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
        */
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Optional<StoreBasicInfoEntity> store =  sRepo.findById(data.getStore());
        Optional<MenuBasicInfoEntity> menu =  mRepo.findById(data.getMenu());
        
        // 메뉴 및 가게 seq가 기존 테이블에 존재하는지 찾기
        /*  if(store.isEmpty()) { 
        //     System.out.println("존재하지 않는 가게 입니다.");
        // }
        // if(menu.isEmpty()) {
        //     System.out.println("존재하지 않는 메뉴입니다.");
        // }
        // if(!store.isPresent()) { 
        //     resultMap.put("status", false);
        //     resultMap.put("message", "존재하지 않는 가게입니다.");
        //     resultMap.put("code", HttpStatus.BAD_REQUEST);
        // }
        // if(!menu.isPresent()) {
        //     resultMap.put("status", false);
        //     resultMap.put("message", "존재하지 않는 메뉴입니다.");
        //     resultMap.put("code", HttpStatus.BAD_REQUEST);
        // } */
        if(store.isEmpty()){
            resultMap.put("message", data.getStore() + "번 가게는 존재하지 않습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        if(menu.isEmpty()){
            resultMap.put("message", data.getMenu() + "번 메뉴는 존재하지 않습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        
        // 중복검사 (연결테이블 내)
        /*     if(store.isPresent() && menu.isPresent()) {
        //     resultMap.put("status", false);
        //     resultMap.put("message", "존재하는 메뉴 및 점포입니다.");
        //     resultMap.put("code", HttpStatus.BAD_REQUEST);
        // }
        // StoreMenuConnectEntity smEntity = smRepo.findByStoreAndMenu(store.get(), menu.get());
        // if(smEntity.getStore().getSbiSeq() != null && smEntity.getMenu().getMbiSeq() != null){
        //     resultMap.put("message", store.get().getSbiSeq() + "번 가게의 " + menu.get().getMbiSeq() + "번 메뉴는 이미 존재합니다..");
        // }*/
        StoreMenuConnectEntity smEntity = smRepo.findByStoreAndMenu(store.get(), menu.get());
        if((smEntity != null) && (smEntity.getStore().getSbiSeq() == data.getStore()) && (smEntity.getMenu().getMbiSeq() == data.getMenu())){
            resultMap.put("message", data.getStore() + "번 가게의 " + data.getMenu() + "번 메뉴는 이미 존재합니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }

        // 조건 만족시 메뉴 입력
        StoreMenuConnectEntity connEntity = StoreMenuConnectEntity.builder()
            .store(sRepo.findById(data.getStore()).get())
            .menu(mRepo.findById(data.getMenu()).get()).build();
        smRepo.save(connEntity);
        resultMap.put("result", true);
        resultMap.put("inserted_data", connEntity);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
        
    }



    // <가게에 등록된 메뉴 삭제하기> -> 점주 로그인 상태에서 본인의 가게에만 설정할 수 있도록
    public ResponseEntity< Map<String, Object> > deleteStoreMenuList(@RequestParam Long storeSeq, @RequestParam Long menuSeq) {
        /* OLD CODE
        // Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        // StoreBasicInfoEntity sbEntity = sRepo.findById(storeSeq).get();
        // MenuBasicInfoEntity mbEntity = mRepo.findById(menuSeq).get();
        // StoreMenuConnectEntity smEntity = smRepo.findByStoreAndMenu(sbEntity, mbEntity);
        // if(smEntity!= null){
        //     smRepo.delete(smEntity);
        //     resultMap.put("message", storeSeq + "번 가게의 " + menuSeq + "번 메뉴가 삭제되었습니다.");
        //     return new ResponseEntity<>(resultMap, HttpStatus.OK);
        // }else{
        //     resultMap.put("message", storeSeq + "번 가게의 " + menuSeq + "번 메뉴는 존재하지 않습니다.");
        //     return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        // }
        */
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Optional<StoreBasicInfoEntity> store =  sRepo.findById(storeSeq);
        Optional<MenuBasicInfoEntity> menu =  mRepo.findById(menuSeq);
        StoreMenuConnectEntity smEntity = smRepo.findByStoreAndMenu(store.get(), menu.get());
        if(smEntity!= null){
            smRepo.delete(smEntity);
            resultMap.put("message", storeSeq + "번 가게의 " + menuSeq + "번 메뉴가 삭제되었습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }else{ // 존재하지 않는 seq 입력시 
            resultMap.put("message", storeSeq + "번 가게의 " + menuSeq + "번 메뉴는 존재하지 않습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
    }


    
}
