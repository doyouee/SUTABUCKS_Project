package com.project1st.starbucks.menu.service;

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

import com.project1st.starbucks.admin.repository.MenuRepository;
import com.project1st.starbucks.menu.entity.MenuBasicInfoEntity;
import com.project1st.starbucks.menu.entity.ProductCategoryEntity;
import com.project1st.starbucks.menu.repository.MenuBasicInfoRepository;
import com.project1st.starbucks.menu.repository.ProductCategoryRepository;
import com.project1st.starbucks.menu.vo.ProductCategoryChildVO;
import com.project1st.starbucks.menu.vo.ProductCategoryVO;

@Service
public class MenuInfoService {
    @Autowired MenuBasicInfoRepository mbiRepo;
    @Autowired ProductCategoryRepository pcRepo;
    @Autowired MenuRepository mRepo;
    
    // <전체 메뉴 조회하기>
    public ResponseEntity<Object> menuList(Pageable pageable) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("list", mRepo.findAll());
        //페이징 처리
        Page<MenuBasicInfoEntity> page = mbiRepo.findAll(pageable);
        resultMap.put("totalPage", page.getTotalPages());
        resultMap.put("totalCount", page.getTotalElements());
        resultMap.put("currentPage", page.getNumber());

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
    


    // <특정 메뉴 조회하기>
    public ResponseEntity<Object> munuDetailList(Long mbiSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        //menu_info 테이블에 존재하지 않는 seq 걸러내기
        Optional<MenuBasicInfoEntity> menuOptional =  mbiRepo.findById(mbiSeq);
        if(menuOptional.isEmpty()){
            resultMap.put("message", mbiSeq + "번 메뉴는 존재하지 않습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        
        // 조건 만족시 특정 메뉴 조회
        resultMap.put("detail", mRepo.findById(mbiSeq));
        return new ResponseEntity<>(resultMap, HttpStatus.CREATED);
    }
        

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
}
