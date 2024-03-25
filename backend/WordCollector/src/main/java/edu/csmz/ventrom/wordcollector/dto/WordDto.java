package edu.csmz.ventrom.wordcollector.dto;

import edu.csmz.ventrom.wordcollector.models.Sentence;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;


//Word data transfer object
@Data
public class WordDto {
    private long id;
    private String word;
    private String       interpretation;
    private String       prefix;
    private String       root;
    private String       subfix;
    private String       explain;
    private List<Sentence> sentences;
}
