package com.project1st.order.menu.service;

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

import com.project1st.order.menu.entity.MenuBasicInfoEntity;
import com.project1st.order.menu.entity.ProductCategoryEntity;
import com.project1st.order.menu.repository.MenuBasicInfoRepository;
import com.project1st.order.menu.repository.ProductCategoryRepository;
import com.project1st.order.menu.vo.ProductCategoryChildVO;
import com.project1st.order.menu.vo.ProductCategoryVO;

@Service
public class MenuService {
    @Autowired MenuBasicInfoRepository mRepo;
    @Autowired ProductCategoryRepository pcRepo;
    
    // <전체 메뉴 조회하기>
    public ResponseEntity<Object> menuList(Pageable pageable) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("list", mRepo.findAll());
        //페이징 처리
        Page<MenuBasicInfoEntity> page = mRepo.findAll(pageable);
        resultMap.put("totalPage", page.getTotalPages());
        resultMap.put("totalCount", page.getTotalElements());
        resultMap.put("currentPage", page.getNumber());

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
    


    // <특정 메뉴 조회하기>
    public ResponseEntity<Object> munuDetailList(Long mbiSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        //menu_info 테이블에 존재하지 않는 seq 걸러내기
        Optional<MenuBasicInfoEntity> menuOptional =  mRepo.findById(mbiSeq);
        if(menuOptional.isEmpty()){
            resultMap.put("message", mbiSeq + "번 메뉴는 존재하지 않습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        
        // 조건 만족시 특정 메뉴 조회
        resultMap.put("detail", mRepo.findById(mbiSeq));
        return new ResponseEntity<>(resultMap, HttpStatus.CREATED);
    }
        
    

    // <전체 카테고리 조회하기 - product_category> -> 안됨
    // public ResponseEntity<Object> categoryList() {
    //     Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    //     // List<ProductCategoryEntity> list = new ArrayList<ProductCategoryEntity>();
    //     resultMap.put("list", pcRepo.findByPcParentSeq());
    //     // for(ProductCategoryEntity p : list) {
    //     //     if(pcRepo.findByPcParentSeq() == null) {
    //     //         list.add(p);
    //     //         resultMap.put("list", list);
    //     //     }
    //     // }
    //     return new ResponseEntity<>(resultMap, HttpStatus.OK);
        
    // }
        


    // 리포지토리에서 parentSeq 없는거 조회해서 걔네 조건 만족하면 pcParent 넣는걸로 해보기 -> 그리고 parentSeq 동일한거 리스트에 넣어서 부르기 
    // public ResponseEntity<Object> categoryList() {
    //     Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    //     List<ProductCategoryEntity> list = pcRepo.findAll();
    //     ProductCategoryVO pcParent = null;
    //     List<ProductCategoryChildVO> pcChild = new ArrayList<ProductCategoryChildVO>();
    //     for(ProductCategoryEntity s : list) {
    //         pcParent = new ProductCategoryVO(s.getPcName(), pcChild);
    //         pcChild.add(new ProductCategoryChildVO(s.getPcName()));
    //     }
    //     // resultMap.put("total", list.size());
    //     resultMap.put("list", new ProductCategoryVO(pcParent.getPcName(), pcChild));
    //     return new ResponseEntity<>(resultMap, HttpStatus.OK);
    // }
    public ResponseEntity<Object> categoryList() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<ProductCategoryEntity> list = pcRepo.findAll();
        ProductCategoryVO pcParent = null;
        List<ProductCategoryChildVO> pcChild = new ArrayList<ProductCategoryChildVO>();
        for(ProductCategoryEntity s : list) {
            if(s.getPcParentSeq() == null) {
                pcParent = new ProductCategoryVO(s.getPcName(), pcChild);
            }
            if(s.getPcParentSeq() != null) {
                pcChild.add(new ProductCategoryChildVO(s.getPcName()));
            }
        }
        // resultMap.put("total", list.size());
        resultMap.put("list", new ProductCategoryVO(pcParent.getPcName(), pcChild));
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }




    
    // Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    // resultMap.put("list", pcRepo.findAll());
    // return new ResponseEntity<>(resultMap, HttpStatus.OK);

    
    // resultMap.put("list", new StoreMenuVO(store, menuList));





        


    // <특정 부모카테고리 조회시 상세 카테고리 조회하기 - product_category> -> 안됨
    // public ResponseEntity<Object> categoryParentList(Integer parentSeq) {
    //     Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    //     resultMap.put("detail", pcRepo.findByPcParentSeq(parentSeq));
    //     return new ResponseEntity<>(resultMap, HttpStatus.CREATED);
    // }
    /* List<ProductCategoryEntity> list = pcRepo.
    // StoreInfoVO store = null;
    // List<MenuInfoVO> menuList = new ArrayList<MenuInfoVO>();
    // for(StoreMenuConnectEntity s : list) {
    //     store = new StoreInfoVO(s.getStore());
    //     menuList.add(new MenuInfoVO(s.getMenu()));
    // }
    // resultMap.put("list", new StoreMenuVO(store, menuList));
        */

    
    // <특정 상세카테고리 조회시 메뉴 나오게하기 - product_category>
}
