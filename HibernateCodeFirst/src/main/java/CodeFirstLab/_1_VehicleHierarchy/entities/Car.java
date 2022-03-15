package CodeFirstLab._1_VehicleHierarchy.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "car")
public class Car extends Vehicle {

    private Integer seats;

    @OneToOne(optional = false)
    @JoinColumn(name = "plate_number_id",
    referencedColumnName = "id")
    private PlateNumber plateNumber;
    @ManyToMany(mappedBy = "car", targetEntity = Drivers.class)
    private Set<Drivers> drivers;

    public PlateNumber getPlateNumber() {
        return plateNumber;
    }

    public Car(String type, String model, BigDecimal price, String fuelType, Integer seats) {
        super(type, model, price, fuelType);
        this.seats = seats;
        this.drivers = new HashSet<>();
    }

    public Car() {
       super();
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }
}
