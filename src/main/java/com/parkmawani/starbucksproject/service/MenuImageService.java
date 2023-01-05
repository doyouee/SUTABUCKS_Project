package com.parkmawani.starbucksproject.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.parkmawani.starbucksproject.entity.MenuImageEntity;
import com.parkmawani.starbucksproject.repository.MenuImageRepository;

@Service

public class MenuImageService {
    @Autowired MenuImageRepository miRepo;
    @Value("${file.image.menuimage}") String menu_img_path;
        public void addEvent(
            MultipartFile miiImgFile,
            Long miiNumber
        ){
            Calendar c = Calendar.getInstance();
            Path menuFolderLocation = Paths.get(menu_img_path);
            
                String menuOriginFileName = miiImgFile.getOriginalFilename();
                String[] iFile = menuOriginFileName.split(("\\."));
                String iExt = iFile[iFile.length-1];
                String iFileName = "";
                for(int i=0;i<iFile.length-1;i++){
                    iFileName += iFile[i];
                }
                String saveMenuFileName = "Menu"+"_";
                saveMenuFileName+=c.getTimeInMillis()+"."+iExt;
                Path menuTargetFile = menuFolderLocation.resolve(miiImgFile.getOriginalFilename());
                
                try {
                    Files.copy(miiImgFile.getInputStream(), menuTargetFile, StandardCopyOption.REPLACE_EXISTING);
                }   catch(Exception e){e.printStackTrace();}
                
                MenuImageEntity menu = MenuImageEntity.builder()
                .miiNumber(miiNumber)
                .miiImgFile(saveMenuFileName)
                .miiUri(iFileName).build();

                menu = miRepo.save(menu);
    }
}
