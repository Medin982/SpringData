package ProductShop.Entites.DTO;

import java.util.List;

public class ProductSoldDTO {
    private int count;
    private List<ProductNameAndPriceDTO> products;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProductNameAndPriceDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductNameAndPriceDTO> products) {
        this.products = products;
    }
}
