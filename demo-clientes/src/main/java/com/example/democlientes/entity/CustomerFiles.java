package com.example.democlientes.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_customer_files")
public class CustomerFiles implements Serializable {

    private static final long serialVersionUID = -2126192645870206799L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @Column(name = "name_file")
    private String nameFile;

    @Column(name = "url")
    private String url;

}
