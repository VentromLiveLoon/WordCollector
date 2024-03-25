package edu.csmz.ventrom.wordcollector.models;

import jakarta.persistence.*;
import lombok.Data;

//Word
@Entity
@Data
@Table(name = "tb_word",indexes = {
        @Index(name = "idx_word",columnList = "word")
})
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "word",nullable = false,unique = true)
    private String word;

    private String interpretation;
    private String prefix;
    private String root;
    private String subfix;
    private String explain;
}
