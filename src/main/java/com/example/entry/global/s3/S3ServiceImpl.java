package com.example.entry.global.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.InaccessibleObjectException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class S3ServiceImpl implements S3Service {

    private final AmazonS3 s3;

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;



    @Override
    public String upload(MultipartFile image) throws IOException {
        if (image.isEmpty()) {
            throw new RuntimeException("이미지가 존재하지않는다");
        }
        String originalFilename = image.getOriginalFilename();
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String randomName = UUID.randomUUID().toString();
        String filename = randomName + "." + ext;

        s3.putObject(new PutObjectRequest(bucket, "image/" + filename, image.getInputStream(), null)
                .withCannedAcl(CannedAccessControlList.AuthenticatedRead));

        return getImageUrl(filename);
    }

    @Override
    public void delete(String objectName) {
        s3.deleteObject(bucket, objectName);
    }

    public String getImageUrl(String imageName) {
        return s3.getUrl(bucket, imageName).toString();
    }

}
