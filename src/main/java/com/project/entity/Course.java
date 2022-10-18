package com.project.entity;

import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "courses")
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String title;

    private int maxNumberOfStudents;

    @ManyToMany(targetEntity = Professor.class, fetch = FetchType.EAGER)
    @RestResource(exported = false)
    private Set<Professor> professors = new HashSet<>();


    public Course(String title, int maxNumberOfStudents) {
        this.title = title;
        this.maxNumberOfStudents = maxNumberOfStudents;
    }

    public Course(int id, String title, int maxNumberOfStudents) {
        this.id = id;
        this.title = title;
        this.maxNumberOfStudents = maxNumberOfStudents;
    }

    public Course(String title, int maxNumberOfStudents, Set<Professor> professors) {
        this.title = title;
        this.maxNumberOfStudents = maxNumberOfStudents;
        this.professors = professors;
    }

    public void addProfessor(Professor professor) {
        professors.add(professor);
    }

    public String toJson() {
        return "{" + "\"id\":" + id + ",\"title\":\"" + title + '\"' +
                ",\"maxNumberOfStudents\":" + maxNumberOfStudents +
                ",\"professors\":" + professors + '}';

    }

    public String printProfessors() {
        StringBuilder result = new StringBuilder();
        professors.forEach(p -> {
            result.append(p.getName()).append("; ");
        });
        return result.toString();
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", title='" + title + '\''
                + ", maxNumberOfStudents=" + maxNumberOfStudents
                + ", professors=" + professors + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id
                && maxNumberOfStudents == course.maxNumberOfStudents
                && title.equals(course.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, maxNumberOfStudents);
    }
}
