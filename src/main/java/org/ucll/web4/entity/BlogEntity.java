package org.ucll.web4.entity;

import java.util.Objects;
import java.util.UUID;

public class BlogEntity {

    private final UUID id;
    private final String title;

    private BlogEntity(String title){
        this.id = UUID.randomUUID();
        this.title = title;
    }

    //getters
    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    //overrides
    @Override
    public boolean equals(Object o){
        if(!(o instanceof BlogEntity)) return false;

        BlogEntity temp = (BlogEntity)o;
        return temp.getTitle().equals(getTitle()) && temp.getId().equals(getId());
    }

    @Override
    public int hashCode(){
        return Objects.hash(title,id);
    }

    //builder
    public static class Builder {
        private String title;

        public Builder withTitle(String title){
            this.title = title;
            return this;
        }

        public BlogEntity build(){
            return new BlogEntity(title);
        }
    }
}
