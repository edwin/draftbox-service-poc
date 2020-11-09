package com.rumahbangsa.draftbox.model.postgres;

import io.quarkus.hibernate.orm.PersistenceUnit;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity

@PersistenceUnit("postgresql")
public class PhoneData extends PanacheEntity {
    public String name;

    public PhoneData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
