package com.busines;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonValue;
import jdk.vm.ci.meta.SerializableConstant;

import java.io.Serializable;
import java.util.Objects;

public class Department implements Serializable {

    private Long id;

    private String name;

    @JsonSetter
    public Long getId() {
        return id;
    }
    @JsonSetter
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
