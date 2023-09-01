package com.example.democlientes.service;

import com.example.democlientes.converter.CustomerFileConverter;
import com.example.democlientes.dto.CustomerFileDTO;
import com.example.democlientes.entity.Customer;
import com.example.democlientes.entity.CustomerFiles;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import javax.naming.directory.InvalidAttributesException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{

    @Value("${configuration.aws.bucket-name}")
    String bucketName;
    @Value("${configuration.aws.bucket-folder}")
    String folderName;
    private final CustomerFileService customerFileService;
    public final CustomerFileConverter converter;
    private final S3Client client;

    @Override
    public Map<String, String> uploadDocument(MultipartFile file, Long id) {

        try {

            Map<String, String> result = new HashMap<>();

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String newFileName = getRandomName(id).concat(getExtensionByStringHandling(fileName));
            String keyValue = folderName + newFileName;

            InputStream inputStream = file.getInputStream();

            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(keyValue)
                    .acl("public-read") //Activar ACL en propiedad del objeto
                    .build();

            client.putObject(request, RequestBody.fromInputStream(inputStream, inputStream.available()));

            result.put("name", newFileName);
            result.put("url", obtainUrlFile(keyValue,client));


            return result;

        } catch (Exception e) {
            throw new RuntimeException("error upload File "+ e);
        }

    }

    @Override
    public CustomerFileDTO uploadFile(MultipartFile file, Customer customer) {

        Map<String, String> customerFileDetail = uploadDocument(file, customer.getId());
        CustomerFiles customerFiles = customerFileService.save(CustomerFiles.builder()
                        .nameFile(customerFileDetail.get("name"))
                        .url(customerFileDetail.get("url"))
                        .customer(customer)
                .build());

        return converter.fromEntity(customerFiles);
    }

    private String getExtensionByStringHandling(String fileName) throws InvalidAttributesException {

        return Optional.ofNullable(fileName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(fileName.lastIndexOf(".") + 1))
                .orElseThrow(InvalidAttributesException::new);

    }

    private String obtainUrlFile(String keyValue, S3Client client) {

        try {

            GetUrlRequest urlRequest = GetUrlRequest.builder()
                    .bucket(bucketName)
                    .key(keyValue)
                    .build();

            return client.utilities().getUrl(urlRequest).toExternalForm();

        } catch (Exception e) {
            throw new RuntimeException("error getting url file s3 " + e);
        }

    }

    private static String getRandomName(Long id) {
        UUID uuid = UUID.randomUUID();
        return "image-"+ id + uuid.toString() + ".";
    }
}
