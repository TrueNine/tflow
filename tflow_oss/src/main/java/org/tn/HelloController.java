package org.tn;

import io.minio.*;
import io.minio.errors.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/")
public class HelloController {

    public HelloController(MinioClient client) {
        this.client = client;
    }

    @GetMapping("hello")
    Object hello() {
        return Map.of("1", 2, "3", 4);
    }

    final MinioClient client;

    @GetMapping("oss")
    Object minio() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        try {
            var exists = client.bucketExists(BucketExistsArgs.builder().bucket("test").build());
            if (!exists) {
                client.makeBucket(MakeBucketArgs.builder().bucket("test").build());
            }

            log.info("bucket is exists = {}", exists);
            log.info("get buckets = {}", client.listBuckets());
            if (exists) {
                client.uploadObject(UploadObjectArgs.builder()
                        .bucket("test")
                        .filename("D:\\project\\maven.txt")
                        .object("maven.txt")
                        .build());
            }
        } catch (ErrorResponseException er) {
            var resp = er.errorResponse();
            log.warn("err Code = {}", resp.code());
            log.warn("err msg = {}", resp.message());

            try (var a = er.response()) {
                log.warn("err http code = {}", a.code());
            }
        }


        return "null";
    }

    @GetMapping("download")
    void download(String b, String n, HttpServletRequest request, HttpServletResponse response) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        log.warn("b = {}, n = {}", b, n);
        var bExists = client.bucketExists(BucketExistsArgs.builder().bucket(b).build());
        if (bExists) {
            var dos = client.getObject(GetObjectArgs.builder()
                    .bucket(b)
                    .object(n)
                    .build());
            var bHeaders = dos.headers();
            log.info("header = {}", bHeaders);


            var d = dos.readAllBytes();


            try (var r = response.getOutputStream();) {
                response.setContentType(bHeaders.get("Content-Type"));
                response.setContentLength(Integer.parseInt(Objects.requireNonNull(bHeaders.get("Content-Length"))));
                response.setHeader("Content-Disposition", "form-data; name=\"fieldName\"; filename=\"" + dos.object() + "\"");

                r.write(d);
            }
        }
    }
}
