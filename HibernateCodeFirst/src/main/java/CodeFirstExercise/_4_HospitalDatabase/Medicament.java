package CodeFirstExercise._4_HospitalDatabase;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(mappedBy = "medicament", targetEntity = Patients.class)
    private Set<Patients> patients;

    @OneToOne(mappedBy = "medicaments", targetEntity = History.class)
    private History history;

    public Medicament(String name) {
        this.name = name;
        this.patients = new HashSet<>();
    }

    public Medicament() {
    }
}
