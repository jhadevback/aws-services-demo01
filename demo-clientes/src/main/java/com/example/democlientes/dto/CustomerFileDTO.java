package com.example.democlientes.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerFileDTO {
    private Long id;
    private String nameFile;
    private String urlFile;
    private Long customerId;
}
