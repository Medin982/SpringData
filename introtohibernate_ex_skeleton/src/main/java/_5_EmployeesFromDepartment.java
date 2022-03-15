import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _5_EmployeesFromDepartment {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();
        String department = "Research and Development";
        entityManager.createQuery("SELECT e FROM Employee e " +
                "WHERE e.department.name = :departmentName" +
                            " ORDER BY e.salary ASC, e.id ASC",
                Employee.class)
                .setParameter("departmentName", department)
                .getResultList().stream()
                .forEach(e -> {
                    System.out.println(String.format("%s %s form %s - %.2f", e.getFirstName(),e.getLastName(), department, e.getSalary()));
                });
    }
}
