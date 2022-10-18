package com.project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "professors")
public class Professor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public Professor(String name) {
        this.name = name;
    }

    public String toJson() {
        return "{" + "\"id\":" + id + ",\"name\":\"" + name + '\"' + '}';
    }

    @Override
    public String toString() {
        return "Professor{" + "id=" + id + ", name='" + name + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return id == professor.id && Objects.equals(name, professor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
