package exam.service.impl;

import exam.model.Town;
import exam.model.dto.ImportTownDTO;
import exam.model.dto.ImportTownRootDTO;
import exam.repository.TownRepository;
import exam.service.TownService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Unmarshaller unmarshaller;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) throws JAXBException {
        this.townRepository = townRepository;

        this.modelMapper = new ModelMapper();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        JAXBContext context = JAXBContext.newInstance(ImportTownRootDTO.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of("src", "main", "resources", "files", "xml", "towns.xml"));
    }

    @Override
    public String importTowns() throws JAXBException, FileNotFoundException {
        ImportTownRootDTO importTownDTO = (ImportTownRootDTO) this.unmarshaller
                .unmarshal(new FileReader(Path.of("src", "main", "resources", "files", "xml", "towns.xml")
                .toAbsolutePath().toString()));

        return importTownDTO
                .getTown()
                .stream()
                .map(this::importTownsDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importTownsDTO(ImportTownDTO dto) {
        Set<ConstraintViolation<ImportTownDTO>> validate = this.validator.validate(dto);
        if(!validate.isEmpty()) {
            return "Invalid Town";
        }

        Town town = this.modelMapper.map(dto, Town.class);
        this.townRepository.save(town);

        return String.format("Successfully imported Town %s", town.getName());
    }
}
