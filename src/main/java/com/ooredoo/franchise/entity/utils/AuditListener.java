package com.ooredoo.franchise.entity.utils;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

public class AuditListener<T extends OoredooBaseEntity>{

    @PrePersist
    public void beforePersist(T e){
        e.setCreatedDate(new Date());
    }

    @PreUpdate
    public void beforeUpdate(T e){
        e.setUpdateDate(new Date());
    }
}
