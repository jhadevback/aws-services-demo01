package com.example.democlientes.controller;

import com.example.democlientes.converter.CustomerConverter;
import com.example.democlientes.dto.CustomerDTO;
import com.example.democlientes.dto.CustomerFileDTO;
import com.example.democlientes.entity.Customer;
import com.example.democlientes.exception.ErrorResponse;
import com.example.democlientes.service.CustomerService;
import com.example.democlientes.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@Tag(name = "Customer", description = "Manage customers")
@RequestMapping(value = "/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerConverter converter;
    private final FileService fileService;

    @GetMapping
    @Operation(summary = "consultar lista de clientes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = CustomerDTO.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "500", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
            })
    })
    public List<CustomerDTO> listCustomer() {
        return converter.fromEntity(customerService.findAll());
    }

    @PostMapping
    @Operation(summary = "guardar clientes")
    public CustomerDTO save(@RequestBody CustomerDTO customer){

        Customer newCustomer = converter.fromDTO(customer);
        return converter.fromEntity(customerService.save(newCustomer));

    }

    @PutMapping("/uploadDocument/{id}")
    @Operation(summary = "actualizar dni clientes")
    public ResponseEntity<CustomerFileDTO> updateDocument(@PathVariable() Long id,
                                                          @RequestParam("file") MultipartFile multipartFile) {

        long size = multipartFile.getSize();
        Optional<Customer> customerById = customerService.findById(id);

        if (size == 0 || customerById.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        CustomerFileDTO customerFileDTO = fileService.uploadFile(multipartFile, customerById.get());

        return new ResponseEntity<CustomerFileDTO>(customerFileDTO, HttpStatus.OK);

    }

}
