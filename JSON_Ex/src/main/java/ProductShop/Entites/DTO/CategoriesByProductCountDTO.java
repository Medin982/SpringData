package ProductShop.Entites.DTO;

import java.math.BigDecimal;

public class CategoriesByProductCountDTO {
    private String category;
    private Long productCount;
    private Double averagePrice;
    private BigDecimal totalRevenue;

    public CategoriesByProductCountDTO(String category, Long productCount, Double averagePrice, BigDecimal totalRevenue) {
        this.category = category;
        this.productCount = productCount;
        this.averagePrice = averagePrice;
        this.totalRevenue = totalRevenue;
    }

    public String getCategory() {
        return category;
    }

    public Long getProductCount() {
        return productCount;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }
}
