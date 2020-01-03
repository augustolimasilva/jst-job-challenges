package model;

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
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @Column(name="username", length = 200, nullable = false)
    private String username;

    @Column(name="password", length = 20, nullable = false)
    private String password;

    @Column(name="email", length = 150, nullable = false)
    private String email;
}
