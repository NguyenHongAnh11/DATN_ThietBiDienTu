package com.PS29729.service.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.PS29729.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {    
    private static final String PROJECT_IMAGE_PATH = System.getProperty("user.dir") + "/src/main/resources/static/assets/images/";

    public File save(MultipartFile file, String folder) {
        File dir = new File(PROJECT_IMAGE_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        String s = System.currentTimeMillis() + file.getOriginalFilename();
        String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
        try {
            File savedFile = new File(dir, name);
            file.transferTo(savedFile);
            System.out.println(savedFile.getAbsolutePath());
            return savedFile;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file", e);
        }
    }
}
