package com.starbucks.final_project01.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.starbucks.final_project01.entity.MenuEntity;
import com.starbucks.final_project01.entity.MenuImageEntity;
import com.starbucks.final_project01.repository.MenuImageRepository;
import com.starbucks.final_project01.repository.MenuRepository;

@Service
public class MenuService {
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
}