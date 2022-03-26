package ProductShop.Entites.DTO;

import java.math.BigDecimal;

public class ProductInRangeDTO {

    private String name;
    private BigDecimal price;
    private String fullName;

    public ProductInRangeDTO(String name, BigDecimal price, String firstName, String lastName) {
        this.name = name;
        this.price = price;
        if (firstName == null) {
            this.fullName = lastName;
        } else {
            this.fullName = firstName + " " + lastName;
        }
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getFullName() {
        return fullName;
    }
}
