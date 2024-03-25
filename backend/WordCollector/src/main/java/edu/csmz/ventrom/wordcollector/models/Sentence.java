package edu.csmz.ventrom.wordcollector.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//Word's sentence
@Entity
@Data
//  Define table name and sentence 's index
@Table(name = "tb_sentence",indexes = {
        @Index(name = "idx_sentence",columnList = "sentence")
})
public class Sentence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "sentence",nullable = false ,unique = true)
    private String sentence;
    @Column(name = "translated",nullable = false)
    private String translated;


}
