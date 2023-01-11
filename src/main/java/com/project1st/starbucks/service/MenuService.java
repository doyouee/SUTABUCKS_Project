package com.project1st.starbucks.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project1st.starbucks.entity.MenuEntity;
import com.project1st.starbucks.entity.MenuImageEntity;
import com.project1st.starbucks.menu.entity.MenuBasicInfoEntity;
import com.project1st.starbucks.menu.entity.ProductCategoryEntity;
import com.project1st.starbucks.menu.repository.MenuBasicInfoRepository;
import com.project1st.starbucks.menu.repository.ProductCategoryRepository;
import com.project1st.starbucks.menu.vo.ProductCategoryChildVO;
import com.project1st.starbucks.menu.vo.ProductCategoryVO;
import com.project1st.starbucks.repository.MenuImageRepository;
import com.project1st.starbucks.repository.MenuRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class MenuService {
    @Autowired MenuBasicInfoRepository mbiRepo;
    @Autowired ProductCategoryRepository pcRepo;
    @Autowired MenuRepository mRepo;
    @Autowired MenuImageRepository miRepo;
    @Value("${file.image.menuimage}") String menu_img_path;

    public void addMenu(
        String mbiName,
        Long miiNumber,
        Integer mbiCost,
        String mbiExplain,
        Long mbiPcSeq,
        MultipartFile miiImgFile
    ) {
        Calendar c = Calendar.getInstance();
        Path menuFolderLocation = Paths.get(menu_img_path);

        String menuOriginFileName = miiImgFile.getOriginalFilename();
        String[] iFile = menuOriginFileName.split(("\\."));
        String iExt = iFile[iFile.length - 1];
        String iFileName = "";
        for (int i = 0; i < iFile.length - 1; i++) {
            iFileName += iFile[i];
        }
        String saveMenuFileName = "Menu" + "_";
        saveMenuFileName += c.getTimeInMillis() + "." + iExt;
        Path menuTargetFile = menuFolderLocation.resolve(miiImgFile.getOriginalFilename());

        try {
            Files.copy(miiImgFile.getInputStream(), menuTargetFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }


        MenuImageEntity menuimg = MenuImageEntity.builder()
            .miiNumber(miiNumber)
            .miiImgFile(saveMenuFileName)
            .miiUri(iFileName).build();
        menuimg = miRepo.save(menuimg);

        MenuEntity menu = MenuEntity.builder()
            .mbiName(mbiName)
            .mbiCost(mbiCost)
            .mbiExplain(mbiExplain)
            .mbiPcSeq(mbiPcSeq).build();

        menu = mRepo.save(menu);
    }

    
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