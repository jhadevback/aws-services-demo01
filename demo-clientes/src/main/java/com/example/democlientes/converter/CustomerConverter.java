package com.example.democlientes.converter;

import com.example.democlientes.dto.CustomerDTO;
import com.example.democlientes.entity.Customer;
import com.example.democlientes.util.Constant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerConverter implements Converter<Customer, CustomerDTO>{

    DateTimeFormatter dateTimeFormatterDTO = DateTimeFormatter.ofPattern(Constant.FORMAT_DATE_TO_DTO);
    DateFormat dateFormat = new SimpleDateFormat(Constant.FORMAT_DATE_TO_DTO);

    @Override
    public CustomerDTO fromEntity(Customer entity) {
        return CustomerDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .lastName(entity.getLastname())
                .age(entity.getAge())
                .date_of_birth(dateFormat.format(entity.getDate_of_birth()))
                .email(entity.getEmail())
                .cellphoneNumber(entity.getCellphoneNumber())
                .build();
    }

    @Override
    public Customer fromDTO(CustomerDTO dto) {

        Date date = new Date();

        try {
            date = dateFormat.parse(dto.getDate_of_birth());
        } catch (Exception e){
            log.info("error convert date");
        }

        return Customer.builder()
                .name(dto.getName())
                .lastname(dto.getLastName())
                .age(dto.getAge())
                .date_of_birth(date)
                .email(dto.getEmail())
                .cellphoneNumber(dto.getCellphoneNumber())
                .build();
    }

    @Override
    public List<CustomerDTO> fromEntity(List<Customer> entities) {
        return entities.stream().map(entity -> CustomerDTO.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .lastName(entity.getLastname())
                        .age(entity.getAge())
                        .date_of_birth(dateFormat.format(entity.getDate_of_birth()))
                        .email(entity.getEmail())
                        .cellphoneNumber(entity.getCellphoneNumber())
                .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> fromDTO(List<CustomerDTO> dtoList) {
        return Converter.super.fromDTO(dtoList);
    }
}
