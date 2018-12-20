package vn.hust.caots.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author caots
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int status = 1;

    public Role() {

    }

    public Role(int id, String name, int status) {
        this.id = id;
        this.name = name;
        this.status= status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

