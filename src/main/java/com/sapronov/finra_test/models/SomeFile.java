package com.sapronov.finra_test.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by fa on 08/12/17.
 */
@Entity
public class SomeFile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String name;

    private String someProperty;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    private Long fileSize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSomeProperty() {
        return someProperty;
    }

    public void setSomeProperty(String someProperty) {
        this.someProperty = someProperty;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date createionTime) {
        this.creationTime = createionTime;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SomeFile someFile = (SomeFile) o;

        return id.equals(someFile.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
