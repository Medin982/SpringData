package exam.model.dto;

import exam.model.enums.WarrantyType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.math.BigDecimal;

public class ImportLaptopDTO {
    @Size(min = 8)
    private String macAddress;

    @Positive
    private double cpuSpeed;

    @Max(128)
    @Min(8)
    private int ram;

    @Max(1024)
    @Min(128)
    private int storage;

    @Size(min = 10)
    private String description;

    @Positive
    private BigDecimal price;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private WarrantyType warrantyType;

    private NameDTOs shop;

    public ImportLaptopDTO() {
    }

    public String getMacAddress() {
        return macAddress;
    }

    public double getCpuSpeed() {
        return cpuSpeed;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public WarrantyType getWarrantyType() {
        return warrantyType;
    }

    public NameDTOs getShop() {
        return shop;
    }
}
