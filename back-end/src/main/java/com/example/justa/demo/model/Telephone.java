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
public class Telephone {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    private Boolean valid;

    private String carrier;

    private String line_type;

    private String number;

    @JsonIgnore
    private Long provider_id;
}
