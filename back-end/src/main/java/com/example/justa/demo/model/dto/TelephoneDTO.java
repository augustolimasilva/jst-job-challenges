package com.example.justa.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TelephoneDTO {

    private Boolean valid;

    private String number;

    private String local_format;

    private String international_format;

    private String country_prefix;

    private String country_code;

    private String country_name;

    private String location;

    private String carrier;

    private String line_type;
}