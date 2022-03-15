import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class _6_AddingANewAddressAndUpdatingEmployee {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        String addressToInsert = "Vitoshka 15";
        Address address = new Address();
        address.setText(addressToInsert);
        entityManager.persist(address);
        Scanner scan = new Scanner(System.in);
        String lastName = scan.nextLine();
        entityManager.createQuery("UPDATE Employee e " +
                "set e.address = :newAddress" +
                " WHERE e.lastName = :last_name")
                .setParameter("newAddress", address)
                .setParameter("last_name", lastName)
                .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
