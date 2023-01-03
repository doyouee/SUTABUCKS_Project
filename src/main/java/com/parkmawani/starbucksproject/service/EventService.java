package com.parkmawani.starbucksproject.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.parkmawani.starbucksproject.repository.EventDetailRepository;
import com.parkmawani.starbucksproject.repository.EventRepository;
import com.parkmawani.starbucksproject.repository.MemberRepository;

@Service
public class EventService {
    @Autowired EventRepository eventRepo;
    @Autowired EventDetailRepository detailRepo;
    @Autowired MemberRepository memberRepo;

    @Value("${file.image.event}") String event_img_path;
    @Value("${file.image.eventdetail}") String detail_img_path;

        public void addEvent(
            Date evStartDate,
            Date evEndDate,
            Date ediStartDate,
            Date ediEndDate,
            @Nullable String evContent,
            @Nullable String ediContent,
            MultipartFile evImgFile,
            MultipartFile ediImgFile
        ){
            Calendar c = Calendar.getInstance();
            String saveeventFileName = "Event"+"_";
            String iFileName="";
            Path eventFolderLocation = Paths.get(event_img_path);
            Path detailFolderLocation = Paths.get(detail_img_path);

                String eventOriginFileName = evImgFile.getOriginalFilename();
                String[] iFile = eventOriginFileName.split(("\\."));
                String iExt = iFile[iFile.length-1];
                for(int i=0;i<iFile.length-1;i++){
                    iFileName += iFile[i];
                }
                saveeventFileName+=c.getTimeInMillis()+"."+iExt;
                Path eventTargetFile = eventFolderLocation.resolve(eventOriginFileName);
                
                try {
                    Files.copy(evImgFile.getInputStream(), eventTargetFile, StandardCopyOption.REPLACE_EXISTING);
                }   catch(Exception e){e.printStackTrace();}
            

                String detailOriginFileName = ediImgFile.getOriginalFilename();
                String[] dFile = detailOriginFileName.split("\\.");
                String dExt = dFile[dFile.length-1];
                String dFileName = "";
                for(int i=0;i<dFile.length-1;i++) {
                    dFileName += dFile[i];
                }
                String saveDetailFileName = "Detail"+"_";
                saveDetailFileName+=c.getTimeInMillis()+"."+dExt;

                Path detailTargetFile = detailFolderLocation.resolve(ediImgFile.getOriginalFilename());
                try {
                    Files.copy(ediImgFile.getInputStream(), detailTargetFile, StandardCopyOption.REPLACE_EXISTING);
                } catch(Exception e){e.printStackTrace();}
    }
}
    



