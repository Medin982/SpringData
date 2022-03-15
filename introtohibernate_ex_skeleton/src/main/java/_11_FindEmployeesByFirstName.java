import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class _11_FindEmployeesByFirstName {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        Scanner scan = new Scanner(System.in);
        String pattern = scan.nextLine();
        entityManager.createQuery("SELECT e FROM Employee e" +
                " WHERE substring(e.firstName, 1, 2) like :pattern", Employee.class)
                .setParameter("pattern", pattern)
                .getResultStream()
                .forEach(e -> {
                    System.out.println(String.format("%s %s - %s - ($%.2f)",
                            e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary()));
                });
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
