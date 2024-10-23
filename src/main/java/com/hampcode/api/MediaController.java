package com.hampcode.api;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hampcode.dto.UploadMediaDTO;
import com.hampcode.service.StorageService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RequestMapping("/media")
@RestController
public class MediaController {
    private final StorageService storageService;
    @PostMapping("/upload")
    public UploadMediaDTO upload(@RequestParam("file") MultipartFile multipartFile) {
        String path = storageService.store(multipartFile);
        return new UploadMediaDTO(path);
    }
    @GetMapping("/{filename}")
    public ResponseEntity<Resource> getResource(@PathVariable String filename) throws IOException {
        Resource resource = storageService.loadAsResource(filename);
        String contentType = Files.probeContentType(resource.getFile().toPath());
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(resource);
    }
}