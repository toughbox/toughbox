package io.toughbox.bucket.service;

import io.minio.errors.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface FileService {
    void uploadFile(MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException,
            IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException,
            InternalException;

    ResponseEntity<byte[]> downloadFile(String uuid) throws ServerException, InsufficientDataException,
            ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException,
            XmlParserException, InternalException;
}
