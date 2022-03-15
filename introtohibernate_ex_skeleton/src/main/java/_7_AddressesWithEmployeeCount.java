import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _7_AddressesWithEmployeeCount {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager
                .createQuery(" SELECT a FROM Address a" +
                                " ORDER BY a.employees.size DESC",
                        Address.class)
                .setMaxResults(10)
                .getResultStream()
                .forEach(a -> {
                    System.out.println(String.format("%s, %s - %d employess",
                            a.getText(), a.getTown() == null ? "" : a.getTown().getName(),
                            a.getEmployees().size()));
                });

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
