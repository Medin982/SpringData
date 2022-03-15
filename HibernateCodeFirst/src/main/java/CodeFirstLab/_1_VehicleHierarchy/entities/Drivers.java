package CodeFirstLab._1_VehicleHierarchy.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "drivers")
public class Drivers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @ManyToMany
    @JoinTable(name = "drivers_cars",
            joinColumns = @JoinColumn(name = "driver_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"))
    private Set<Car> car;

    public Drivers(String fullName) {
        this.fullName = fullName;
        this.car = new HashSet<>();
    }

    public Drivers() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Car> getCar() {
        return car;
    }

    public void setCar(Set<Car> car) {
        this.car = car;
    }
}
