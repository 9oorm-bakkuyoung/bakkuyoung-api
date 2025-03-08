package com.goorm.bakkuyoungapi.global.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${server.base-url}")
    private String baseUrl; //todo. 경로 수정

    public String upload(MultipartFile file) {
        try {
            //파일 이름 고유화
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            //저장 경로 생성
            Path path = Paths.get(uploadDir, fileName);
            Files.createDirectories(path.getParent());
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 중 오류 :" + e);
        }
    }

    public String getFullPath(String fileName) {
        return baseUrl + "/uploads/" + fileName;
    }

}
