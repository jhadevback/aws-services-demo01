package com.example.democlientes.converter;

import com.example.democlientes.dto.CustomerFileDTO;
import com.example.democlientes.entity.CustomerFiles;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerFileConverter implements Converter<CustomerFiles, CustomerFileDTO>{
    @Override
    public CustomerFileDTO fromEntity(CustomerFiles entity) {

        return CustomerFileDTO.builder()
                .id(entity.getId())
                .nameFile(entity.getNameFile())
                .urlFile(entity.getUrl())
                .customerId(entity.getCustomer().getId())
                .build();
    }

    @Override
    public CustomerFiles fromDTO(CustomerFileDTO dto) {
        return null;
    }
}
