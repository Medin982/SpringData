package CodeFirstExercise._5_BillsPaymentSystem;

import javax.persistence.*;
import java.time.Month;
import java.time.Year;
import java.util.Date;

@Entity
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "card_type", nullable = false)
    private String cardType;
    @Column(name = "expiration_month", nullable = false)
    private Month expirationMonth;
    @Column(name = "expiration_year", nullable = false)
    private Year expirationYear;
    @OneToOne(mappedBy = "creditCard", targetEntity = Billing.class)
    private Billing billing;

    public CreditCard(String cardType, Month expirationMonth, Year expirationYear) {
        this.cardType = cardType;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
    }

    public CreditCard() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Month getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(Month expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public Year getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(Year expirationYear) {
        this.expirationYear = expirationYear;
    }
}
