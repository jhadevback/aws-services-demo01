package com.example.democlientes.service;

import com.example.democlientes.dto.CustomerFileDTO;
import com.example.democlientes.entity.Customer;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface FileService {
    Map<String, String> uploadDocument(MultipartFile file, Long id);
    CustomerFileDTO uploadFile(MultipartFile file, Customer customer);
}
