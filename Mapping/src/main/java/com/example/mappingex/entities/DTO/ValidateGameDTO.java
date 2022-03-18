package com.example.mappingex.entities.DTO;

import com.example.mappingex.Exceptions.ValidationExceptions;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateGameDTO {

    private String title;

    private BigDecimal price;

    private float size;

    private String trailer;

    private String thumbnail;

    private String description;

    private LocalDate releaseDate;

    public ValidateGameDTO(String title, BigDecimal price, float size,
                           String trailer, String thumbnail,
                           String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.thumbnail = thumbnail;
        this.description = description;
        this.releaseDate = releaseDate;
        validateDate();
    }

    private void validateDate() {
        Pattern pattern = Pattern.compile("[A-Z][A-Za-z]{2,100}");
        Matcher matcher = pattern.matcher(this.title);
        if (!matcher.find()) {
            throw new ValidationExceptions("Invalid title name");
        }

        if (this.price.intValue() <= 0) {
            throw new ValidationExceptions("Price must be positive number");
        }

        if (this.size <= 0) {
            throw new ValidationExceptions("Price must be positive number");
        }

        if (this.trailer.length() != 11) {
            throw new ValidationExceptions("Wrong ID, must be exactly 11 characters");
        }

        if (!this.thumbnail.contains("http://")) {
            if (!this.thumbnail.contains("https://")) {
                throw new ValidationExceptions("It should be a plain text starting with 'http://, https://'");
            }
        }

        if (this.description.length() < 20) {
            throw new ValidationExceptions("Must be at least 20 symbols");
        }
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public float getSize() {
        return size;
    }

    public String getTrailer() {
        return trailer;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
