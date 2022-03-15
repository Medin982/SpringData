import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _10_IncreaseSalaries {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("UPDATE Employee e" +
            " SET e.salary = e.salary * 1.12" +
                " WHERE e.department.id IN (1, 2, 4, 11)")
                .executeUpdate();
        entityManager.createQuery("SELECT e FROM Employee e" +
                " WHERE e.department.id IN (1, 2, 4, 11)", Employee.class)
                .getResultStream()
                .forEach(e -> {
                    System.out.println(String.format("%s %s ($%.2f)",
                            e.getFirstName(), e.getLastName(), e.getSalary()));
                });
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
