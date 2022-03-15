package CodeFirstExercise._5_BillsPaymentSystem;

import javax.persistence.*;

@Entity
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "bank_name", nullable = false)
    private String bankName;
    @Column(name = "SWIFT_code", nullable = false)
    private String SwiftCode;
    @OneToOne(mappedBy = "bankAccount", targetEntity = Billing.class)
    private Billing billing;

    public BankAccount(String bankName, String swiftCode) {
        this.bankName = bankName;
        SwiftCode = swiftCode;
    }

    public BankAccount() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSwiftCode() {
        return SwiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        SwiftCode = swiftCode;
    }
}
