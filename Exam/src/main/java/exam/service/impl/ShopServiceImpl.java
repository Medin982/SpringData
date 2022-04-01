package exam.service.impl;

import exam.model.Shop;
import exam.model.Town;
import exam.model.dto.ImportShopDTO;
import exam.model.dto.ImportShopRootDTO;
import exam.repository.ShopRepository;
import exam.repository.TownRepository;
import exam.service.ShopService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final TownRepository townRepository;

    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Unmarshaller unmarshaller;


    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository, TownRepository townRepository) throws JAXBException {
        this.shopRepository = shopRepository;
        this.townRepository = townRepository;

        this.modelMapper = new ModelMapper();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        JAXBContext context = JAXBContext.newInstance(ImportShopRootDTO.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return Files.readString(Path.of("src", "main", "resources", "files", "xml", "shops.xml"));
    }

    @Override
    public String importShops() throws JAXBException, FileNotFoundException {
        ImportShopRootDTO importShops = (ImportShopRootDTO) this.unmarshaller
                .unmarshal(new FileReader(Path.of("src", "main", "resources", "files", "xml", "shops.xml")
                        .toAbsolutePath().toString()));
        return importShops
                .getShop()
                .stream()
                .map(this::importShopDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importShopDTO(ImportShopDTO dto) {
        Set<ConstraintViolation<ImportShopDTO>> validate = this.validator.validate(dto);
        if(!validate.isEmpty()) {
            return "Invalid Shop";
        }

        Optional<Shop> shopByName = this.shopRepository.findByName(dto.getName());
        if (shopByName.isPresent()) {
            return "Invalid Shop";
        }
        Optional<Town> town = this.townRepository.findByName(dto.getTown().getName());
        Shop shop = this.modelMapper.map(dto, Shop.class);
        shop.setTown(town.get());
        this.shopRepository.save(shop);

        return String.format("Successfully imported Shop %s - %d", shop.getName(), shop.getIncome());
    }
}
