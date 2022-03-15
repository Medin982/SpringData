package CodeFirstLab._1_VehicleHierarchy.entities;

import javax.persistence.*;
@Entity()
public class Companies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public Companies(String name) {
        this.name = name;
    }

    public Companies() {}

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
}
