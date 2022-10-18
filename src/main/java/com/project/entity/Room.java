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
@Table(name = "rooms")
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int seatingCapacity;

    public Room(String name, int seatingCapacity) {
        this.name = name;
        this.seatingCapacity = seatingCapacity;
    }

    public String toJson() {
        return "{\"id\":" + id + ",\"name\":\"" + name + '\"' + ",\"seatingCapacity\":" + seatingCapacity + '}';

    }

    @Override
    public String toString() {
        return "Room{" + "id=" + id + ", name='" + name + '\'' + ", seatingCapacity=" + seatingCapacity + '}';

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id && seatingCapacity == room.seatingCapacity && Objects.equals(name, room.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, seatingCapacity);
    }
}
