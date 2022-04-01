package exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exam.model.Customer;
import exam.model.Town;
import exam.model.dto.ImportCustomersDTO;
import exam.repository.CustomerRepository;
import exam.repository.TownRepository;
import exam.service.CustomerService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final TownRepository townRepository;

    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Gson gson;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, TownRepository townRepository) {
        this.customerRepository = customerRepository;
        this.townRepository = townRepository;

        this.modelMapper = new ModelMapper();
        this.modelMapper.addConverter(new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(MappingContext<String, LocalDate> mappingContext) {
                return LocalDate.parse(mappingContext.getSource(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            }
        });
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        this.gson = new GsonBuilder().create();

    }

    @Override
    public boolean areImported() {
        return this.customerRepository.count() > 0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return Files.readString(Path.of("src", "main", "resources", "files", "json", "customers.json"));
    }

    @Override
    public String importCustomers() throws IOException {
        ImportCustomersDTO[] importCustomersDTOS = this.gson.fromJson(this.readCustomersFileContent(), ImportCustomersDTO[].class);
        return Arrays.stream(importCustomersDTOS)
                .map(this::importCustomerDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importCustomerDTO(ImportCustomersDTO dto) {
        Set<ConstraintViolation<ImportCustomersDTO>> validate = this.validator.validate(dto);
        if (!validate.isEmpty()) {
            return "Invalid Customer";
        }

        Optional<Customer> customerByEmail = this.customerRepository.findByEmail(dto.getEmail());
        if (customerByEmail.isPresent()) {
            return "Invalid Customer";
        }
        Optional<Town> town = this.townRepository.findByName(dto.getTown().getName());
        Customer customer = this.modelMapper.map(dto, Customer.class);
        customer.setTown(town.get());
        this.customerRepository.save(customer);
        return String.format("Successfully imported Customer %s %s - %s",
                customer.getFirstName(), customer.getLastName(), customer.getEmail());
    }
}
