package com.digiboy.platform.scheduler.to;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {

    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
