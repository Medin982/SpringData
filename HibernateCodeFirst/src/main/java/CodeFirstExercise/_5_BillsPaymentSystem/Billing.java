package CodeFirstExercise._5_BillsPaymentSystem;

import javax.persistence.*;

@Entity
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int number;

    private String owner;
    @OneToOne(optional = false)
    @JoinColumn(name = "credit_card_id", referencedColumnName = "id")
    private CreditCard creditCard;
    @OneToOne(optional = false)
    @JoinColumn(name = "bank_account_id", referencedColumnName = "id")
    private BankAccount bankAccount;
    @ManyToOne(optional = false)
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private Users users;

    public Billing(int number, String owner) {
        this.number = number;
        this.owner = owner;
    }

    public Billing() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
