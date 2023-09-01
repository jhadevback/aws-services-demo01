package com.example.democlientes.service;

import com.example.democlientes.entity.CustomerFiles;
import com.example.democlientes.repository.CustomerFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerFileServiceImpl implements CustomerFileService{

    private final CustomerFileRepository customerFileRepository;


    @Override
    public List<CustomerFiles> findAll() {
        return customerFileRepository.findAll();
    }

    @Override
    public Optional<CustomerFiles> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public CustomerFiles save(CustomerFiles customerFiles) {
        return customerFileRepository.save(customerFiles);
    }

    @Override
    public CustomerFiles update(CustomerFiles customerFiles) {
        return null;
    }

    @Override
    public CustomerFiles updatePartial(CustomerFiles customerFiles) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
