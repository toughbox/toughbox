package io.toughbox.bucket.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.toughbox.bucket.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/minio")
public class MinioController {

    private final FileService fileService;

    @GetMapping("")
    public String index() {
        return "Minio server";
    }

    @Operation(summary = "파일 업로드", description = "minIO 버킷에 파일 업로드")
    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Boolean> upload(@RequestParam(name = "file", required = true) MultipartFile file) throws Exception {
        fileService.uploadFile(file);
        return ResponseEntity.ok(true);
    }

    @Operation(summary = "파일 다운로드", description = "minIO 버킷의 파일 다운로드")
    @GetMapping("/download/{uuid}")
    public ResponseEntity<byte[]> download(@PathVariable String uuid) throws Exception {
        return fileService.downloadFile(uuid);
    }
}
