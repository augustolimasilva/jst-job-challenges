package com.example.justa.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    private String cep;

    private String place;

    private String complement;

    private String district;

    private String location;

    private String uf;

    @JsonIgnore
    private Long user_id;
}