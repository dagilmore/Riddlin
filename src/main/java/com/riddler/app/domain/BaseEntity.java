package com.riddler.app.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@SuppressWarnings("serial")
public abstract class BaseEntity implements Serializable {
    @Id
    private String id;

    public String getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return (id == null) ? 0 : id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        BaseEntity other = (BaseEntity) obj;
        if (id == null) return other.id == null;
        return id.equals(other.id);
    }
}
