package edu.csmz.ventrom.wordcollector.models;

import jakarta.persistence.*;
import lombok.Data;

//User data table defination
@Entity
@Data
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;

}
