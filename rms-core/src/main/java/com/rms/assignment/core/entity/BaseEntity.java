package com.rms.assignment.core.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Base entity for all entities to be persisted. All entities have
 * 
 * <ol>
 * <li>id property that maps to Entity primary key.
 * <li>version property that is used for data versioning and optimistic locking
 * </ol>
 * 
 * @author Rohit
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // unique identifier for data.

    @Version
    private short version; // data versioning for optimistic locking.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public short getVersion() {
        return version;
    }
}
