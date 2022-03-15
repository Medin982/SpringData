package CodeFirstLab._1_VehicleHierarchy.entities;

import javax.persistence.*;

@Entity
@Table(name = "plate_number")
public class PlateNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String number;


    public PlateNumber() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
