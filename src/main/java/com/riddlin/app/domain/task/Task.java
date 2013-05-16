package com.riddlin.app.domain.task;

import com.riddlin.app.domain.BaseEntity;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Document(collection = "Task")
public class Task extends BaseEntity
{
    @NotEmpty
    private String task;
    
    @NotEmpty
    private String answer;

    public Task()
    {
    }

    public String getTask()
    {
        return task;
    }

    public void setTask(String task)
    {
        this.task = task;
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