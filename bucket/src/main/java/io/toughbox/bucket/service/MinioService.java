package io.toughbox.bucket.service;

import io.minio.*;
import io.minio.errors.*;
import io.toughbox.bucket.entity.FileInfo;
import io.toughbox.bucket.repository.FileInfoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MinioService {

    private final MinioClient minioClient;
    private final FileInfoRepository fileInfoRepository;
    private final String bucketName = "portfolio";

    public void uploadFile(MultipartFile file) throws Exception {

        checkBucket();

        String uuid = UUID.randomUUID().toString();
        String originName = file.getOriginalFilename();
        String ext = originName != null && originName.contains(".") ?
                originName.substring(originName.lastIndexOf('.') + 1) : "";
        String fileName = uuid + "." + ext;
        String contentType = file.getContentType();
        long size = file.getSize();
        String path = ""; // 필요 시 버킷 내 경로 지정

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );

        FileInfo fileInfo = FileInfo.builder()
                .uuid(uuid)
                .originName(originName)
                .ext(ext)
                .size(size)
                .contentType(contentType)
                .bucket(bucketName)
                .path(path)
                .uploaderId(1L)
                .status("ACTIVE")
                .build();
        fileInfoRepository.save(fileInfo);
    }

    public ResponseEntity<byte[]> downloadFile(String uuid) throws Exception {

        FileInfo fileInfo = fileInfoRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("File not found!"));

        String fileName = uuid + "." + fileInfo.getExt();
        GetObjectResponse objectResponse = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .build()
        );

        byte[] bytes = IOUtils.toByteArray(objectResponse);
        String downloadFileName = URLEncoder.encode(fileInfo.getOriginName(), "UTF-8").replaceAll("\\+", "%20");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); /* 브라우저가 직접 열지 말고 다운로드 강제하는 옵션 */
        headers.setContentDispositionFormData("attachment", downloadFileName);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + downloadFileName);

        return ResponseEntity.ok()
                .headers(headers)
                .body(bytes);
    }

    private void checkBucket() throws ServerException, InsufficientDataException, ErrorResponseException, IOException,
            NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }
}
