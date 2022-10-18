package com.project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "meeting_time")
public class MeetingTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String time;

    public String toJson() {
        return "{" + "\"id\":" + id + ",\"time\":\"" + time + "\"}";
    }

    @Override
    public String toString() {
        return "MeetingTime{" + "id=" + id + ", time='" + time + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetingTime that = (MeetingTime) o;
        return id == that.id && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time);
    }
}
