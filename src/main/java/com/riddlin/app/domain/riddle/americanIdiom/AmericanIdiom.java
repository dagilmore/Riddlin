package com.riddlin.app.domain.riddle.americanIdiom;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document(collection = "AmericanIdioms")
public class AmericanIdiom implements Serializable
{
    @Id
    private Long id;
    
    private String idiom;
    private String meaning;

    @Transient
    private String reg;

    public AmericanIdiom() {
        reg = "[^\\s]";
    }

    public Long getId() {
        return id;
    }

    public String getIdiom() {
        return idiom;
    }

    public void setIdiom(String idiom) {
        this.idiom = idiom;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getClue(char c) {
        String nextChar = "|^" + c + "]";
        reg = reg.substring(0,reg.length()-1) + nextChar;
        reg.replaceAll("]",reg);
        return idiom.replaceAll(reg,"_");
    }

    public String getInitialState() {
        return this.getClue(' ');
    }

    public String toString() {
        return "Idiom: " + idiom + "\nMeaning: " + meaning;
    }

}