package com.example.justa.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProviderDTO {

    @Length(min = 5, max = 200, message = "{provider.name.length}")
    @NotNull(message = "{provider.name.notnull}")
    private String name;

    @CNPJ(message = "{provider.cnpj.valid}")
    @NotNull(message = "{provider.cnpj.notnull}")
    private String cnpj;

    @Length(min = 10, max = 150, message = "{provider.email.length}")
    @NotNull(message = "{provider.email.notnull}")
    @Email(message = "{provider.email.valid}")
    private String email;

    @NotNull(message = "{provider.cep.notnull}")
    @Length(min = 8, max = 8, message = "{provider.cep.length}")
    private String cep;

    @NotNull(message = "{provider.telephone.notnull}")
    @Length(min = 11, max = 11, message = "{provider.telephone.length}")
    private String telephone;
}
