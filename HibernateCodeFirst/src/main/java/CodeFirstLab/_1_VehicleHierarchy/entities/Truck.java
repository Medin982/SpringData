package CodeFirstLab._1_VehicleHierarchy.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity(name = "truck")
public class Truck extends Vehicle {

    @Column(name = "load_capacity")
    private Double loadCapacity;

    public Truck(String type, String model, BigDecimal price, String fuelType, Double loadCapacity) {
        super(type, model, price, fuelType);
        this.loadCapacity = loadCapacity;
    }

    public Truck() {
        super();
    }
}
