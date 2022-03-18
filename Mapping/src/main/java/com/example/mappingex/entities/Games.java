package com.example.mappingex.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Games {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String title;

    private String trailer;

    private String thumbnail;
    @Column(nullable = false)
    private float size;
    @Column(nullable = false)
    private BigDecimal price;

    private String description;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @ManyToMany(mappedBy = "games", targetEntity = User.class)
    private Set<User> users;
    @ManyToMany(mappedBy = "products", targetEntity = Orders.class)
    private Set<Orders> orders;

    public Games() {
        this.users = new HashSet<>();
        this.orders = new HashSet<>();
    }

    public Games(String title, String trailer, String thumbnail, float size,
                 BigDecimal price, String description, LocalDate releaseDate) {
        this();
        this.title = title;
        this.trailer = trailer;
        this.thumbnail = thumbnail;
        this.size = size;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String TitleAndPriceInformation() {
        return String.format("%s %.2f%n", getTitle(), getPrice());
    }

    @Override
    public String toString() {
        return String.format("Title: %s%n" +
                "Trailer: %s%n" +
                "Thumbnail: %s%n" +
                "Size: %.2f%n" +
                "Price: %.2f%n" +
                "Description: %s%n" +
                "ReleaseDate: %s", getTitle(), getTrailer(), getThumbnail(),
                getSize(), getPrice(), getDescription(), getReleaseDate().toString());

    }
}
