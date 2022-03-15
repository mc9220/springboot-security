package com.Demointelli.demo2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long uid;
    private String uname;
    private  String password;
    private String email;

    @OneToOne
    @JoinColumn(name = "authority_id")
    private Authority authority;

}
