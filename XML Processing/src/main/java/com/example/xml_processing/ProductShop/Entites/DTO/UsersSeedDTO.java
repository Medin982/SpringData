package com.example.xml_processing.ProductShop.Entites.DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersSeedDTO {

    @XmlElement(name = "user")
    private List<UserSeedDTO> users;

    public UsersSeedDTO() {
    }

    public List<UserSeedDTO> getUsers() {
        return users;
    }
}
