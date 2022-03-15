package CodeFirstExercise._1_GringottsDatabase;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "wizard_deposits")
public class WizardDeposits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, name = "first_name")
    private String firstName;
    @Column(length = 60, name = "last_name")
    private String lastName;
    @Column(length = 1000)
    private String notes;

    private int age;
    @Column(length = 100, name = "magic_wand_creator")
    private String magicWandCreator;
    @Column(name = "magic_wand_size")
    private int magicWandSize;
    @Column(length = 20, name = "deposit_group")
    private String depositGroup;
    @Column(name = "deposit_start_date")
    private LocalDateTime depositStartDate;
    @Column(name = "deposit_amount")
    private float depositAmount;
    @Column(name = "deposit_interest")
    private float depositInterest;
    @Column(name = "deposit_charge")
    private float depositCharge;
    @Column(name = "deposit_expiretion_date")
    private LocalDateTime depositExpirationDate;
    @Column(name = "is_deposit_expired")
    private boolean isDepositExpired;


}
