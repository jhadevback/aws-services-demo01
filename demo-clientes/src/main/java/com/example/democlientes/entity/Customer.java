package com.example.democlientes.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = -3766027074552721535L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "lastname", length = 50, nullable = false)
    private String lastname;

    @Column(name = "age")
    private Integer age;

    @Column(name = "date_of_birth", length = 50, nullable = false)
    private Date date_of_birth;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "cellphone_number", length = 50, nullable = false)
    private String cellphoneNumber;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CustomerFiles> customerFiles = new HashSet<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Order> customerOrders = new HashSet<>();

}
