package CarDealer.Entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String make;

    private String model;

    private int distance;

    @OneToOne(mappedBy = "car", targetEntity = Sale.class)
    private Sale sale;

    @ManyToMany(mappedBy = "car", targetEntity = Parts.class)
    private Set<Parts> parts;

    public Cars(String make, String model, int distance) {
        this();
        this.make = make;
        this.model = model;
        this.distance = distance;
    }

    public Cars() {
        this.parts = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
