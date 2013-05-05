package com.riddlin.app.domain.riddle;

import com.riddlin.app.domain.BaseEntity;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document(collection = "Riddle")
public class Riddle extends BaseEntity
{
    private String riddle;
    private String answer;

    public Riddle()
    {
    }

    public String getRiddle()
    {
        return riddle;
    }

    public void setRiddle(String riddle)
    {
        this.riddle = riddle;
    }

    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    public String toString()
    {
        String ret = "";
        return ret;
    }

}