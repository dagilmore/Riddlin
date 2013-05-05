package com.riddlin.app.domain.system;

import org.springframework.data.mongodb.core.mapping.Document;

import com.riddlin.app.domain.BaseEntity;

/**
 * Entity for counter.
 */
@SuppressWarnings("serial")
@Document(collection = "Counter")
public class Counter extends BaseEntity{
    
    private String name;
    
    private long sequence;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the sequence
     */
    public long getSequence() {
        return sequence;
    }

    /**
     * @param sequence the sequence to set
     */
    public void setSequence(long sequence) {
        this.sequence = sequence;
    }
    
    Counter() {}
}
