package CodeFirstLab._1_VehicleHierarchy.entities;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity(name = "bike")
public class Bike extends Vehicle{

    public Bike(String type, String model, BigDecimal price, String fuelType) {
        super(type, model, price, fuelType);
    }

    public Bike() {
        super();
    }
}
