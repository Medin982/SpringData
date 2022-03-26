package ProductShop.Entites;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private int age;

    @OneToMany(mappedBy = "seller", targetEntity = Product.class)
    private Set<Product> productsBuy;

    @OneToMany(mappedBy = "buyer", targetEntity = Product.class
    ,fetch = FetchType.EAGER)
    private Set<Product> productsSell;

    @ManyToMany
    @JoinTable(name = "users_friends",
    joinColumns =
    @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns =
    @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    private Set<User> friends;

    public User() {
        this.productsBuy = new HashSet<>();
        this.productsSell = new HashSet<>();
        this.friends = new HashSet<>();
    }

    public User(String firstName, String lastName, int age) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Product> getProductsBuy() {
        return productsBuy;
    }

    public void setProductsBuy(Set<Product> productsBuy) {
        this.productsBuy = productsBuy;
    }

    public Set<Product> getProductsSell() {
        return productsSell;
    }

    public void setProductsSell(Set<Product> productsSell) {
        this.productsSell = productsSell;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }
}
