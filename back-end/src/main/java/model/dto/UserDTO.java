package model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @Length(min = 5, max = 200, message = "{user.username.length}")
    @NotNull(message = "{user.username.notnull}")
    private String username;

    @Length(min = 5, max = 20, message = "{user.password.length}")
    @NotNull(message = "{user.password.notnull}")
    private String password;

    @Length(min = 10, max = 150, message = "{user.email.length}")
    @NotNull(message = "{user.email.notnull}")
    @Email(message = "{user.email.valid}")
    private String email;
}
