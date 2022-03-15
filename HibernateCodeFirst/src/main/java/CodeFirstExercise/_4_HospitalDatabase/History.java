package CodeFirstExercise._4_HospitalDatabase;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "visitations_id", referencedColumnName = "id")
    private Visitations visitations;

    @ManyToOne(optional = false)
    @JoinColumn(name = "diagnoses_id", referencedColumnName = "id")
    private Diagnose diagnose;

    @OneToOne(optional = false)
    @JoinColumn(name = "medicament_id", referencedColumnName = "id")
    private Medicament medicaments;

    public History(Visitations visitations, Diagnose diagnose) {
        this.visitations = visitations;
        this.diagnose = diagnose;
    }

    public History() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Visitations getVisitations() {
        return visitations;
    }

    public void setVisitations(Visitations visitations) {
        this.visitations = visitations;
    }

    public Diagnose getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
    }
}
