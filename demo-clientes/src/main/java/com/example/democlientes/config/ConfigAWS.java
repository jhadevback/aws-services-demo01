package com.example.democlientes.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class ConfigAWS {

    @Value("${cloud.aws.credentials.access-key}")
    private String awsAccessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String awsSecretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Primary
    @Bean
    public S3Client amazonS3Client() {

        AwsBasicCredentials awsCredential = AwsBasicCredentials.create(awsAccessKey, awsSecretKey);
        return S3Client.builder().credentialsProvider(StaticCredentialsProvider
                .create(awsCredential)).region(Region.SA_EAST_1).build();

    }


}
