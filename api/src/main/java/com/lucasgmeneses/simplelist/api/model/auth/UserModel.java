package com.lucasgmeneses.simplelist.api.model.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema = "auth", name = "TB_USER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(unique = true,nullable = false)
    private String email;

    @Column
    private Date dateCreated;

    @Column
    private Date dateUpdated;

}
