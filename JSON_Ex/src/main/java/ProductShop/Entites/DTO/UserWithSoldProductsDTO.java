package ProductShop.Entites.DTO;

import java.util.List;

public class UserWithSoldProductsDTO {

    private String firstName;
    private String lastName;
    private List<SoldProductDTO> productsSell;

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

    public List<SoldProductDTO> getProductsSell() {
        return productsSell;
    }

    public void setProductsSell(List<SoldProductDTO> productsSell) {
        this.productsSell = productsSell;
    }
}
