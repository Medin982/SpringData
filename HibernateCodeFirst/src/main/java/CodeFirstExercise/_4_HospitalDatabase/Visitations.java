package CodeFirstExercise._4_HospitalDatabase;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Visitations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date date;
    @Column(length = 2000)
    private String comments;

    @OneToMany(mappedBy = "visitations", targetEntity = History.class)
    private Set<History> history;

    public Visitations(Date date, String comments) {
        this.date = date;
        this.comments = comments;
        this.history = new HashSet<>();
    }

    public Visitations() {
    }

    public Set<History> getHistory() {
        return history;
    }

    public void setHistory(Set<History> history) {
        this.history = history;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
