package CodeFirstLab._1_VehicleHierarchy.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity(name = "plane")
public class Plane extends Vehicle {

    @Column(name = "passenger_capacity")
    private Integer passengerCapacity;

    @ManyToOne
    @JoinColumn(name = "company_id",
    referencedColumnName = "id")
    private Companies companies;

    public Companies getCompanies() {
        return companies;
    }

    public Plane(String type, String model, BigDecimal price, String fuelType, Integer passengerCapacity) {
        super(type, model, price, fuelType);
        this.passengerCapacity = passengerCapacity;
    }

    public Plane() {
        super();
    }
}
