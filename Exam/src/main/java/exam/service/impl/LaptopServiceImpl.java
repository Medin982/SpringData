package exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exam.model.Laptop;
import exam.model.Shop;
import exam.model.dto.ImportLaptopDTO;
import exam.model.enums.WarrantyType;
import exam.repository.LaptopRepository;
import exam.repository.ShopRepository;
import exam.service.LaptopService;
import exam.util.ValidationUtil;
import exam.util.ValidationUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LaptopServiceImpl implements LaptopService {

    private final LaptopRepository laptopRepository;
    private final ShopRepository shopRepository;

    private final ModelMapper modelMapper;
    private final Gson gson;
    private final Validator validator;

    @Autowired
    public LaptopServiceImpl(LaptopRepository laptopRepository, ShopRepository shopRepository) {
        this.laptopRepository = laptopRepository;
        this.shopRepository = shopRepository;

        this.modelMapper = new ModelMapper();
        this.gson = new GsonBuilder().create();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public boolean areImported() {
        return this.laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return Files.readString(Path.of("src", "main", "resources", "files", "json", "laptops.json"));
    }

    @Override
    public String importLaptops() throws IOException {
        ImportLaptopDTO[] importLaptopDTOS = this.gson.fromJson(this.readLaptopsFileContent(), ImportLaptopDTO[].class);
        return Arrays.stream(importLaptopDTOS)
                .map(this::importLaptopDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importLaptopDTO(ImportLaptopDTO dto) {
        Set<ConstraintViolation<ImportLaptopDTO>> validate = this.validator.validate(dto);
        if (!validate.isEmpty()) {
            return "Invalid Laptop";
        }

        Optional<Laptop> laptopByMacAddress = this.laptopRepository.findByMacAddress(dto.getMacAddress());
        if (laptopByMacAddress.isPresent()) {
            return "Invalid Laptop";
        }
        Laptop laptop = this.modelMapper.map(dto, Laptop.class);
        Optional<Shop> shop = this.shopRepository.findByName(dto.getShop().getName());
        laptop.setShop(shop.get());
        this.laptopRepository.save(laptop);
        return String.format("Successfully imported Laptop %s - %.2f - %d - %d",
                laptop.getMacAddress(), laptop.getCpuSpeed(), laptop.getRam(), laptop.getStorage());
    }

    @Override
    public String exportBestLaptops() {
        return this.laptopRepository
                .findAllOrderByCpuSpeedDescRamDescStorageDescMacAddressAsc()
                .stream()
                .map(Laptop::toString)
                .collect(Collectors.joining("\n"));
    }
}
