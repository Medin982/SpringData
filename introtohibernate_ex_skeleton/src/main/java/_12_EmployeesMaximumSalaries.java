import entities.Department;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class _12_EmployeesMaximumSalaries {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        List<BigDecimal> resultList = entityManager.createQuery(
                "SELECT MAX(e.salary)  FROM Employee e" +
                        " GROUP BY e.department.id", BigDecimal.class)
                .getResultList();
        List<Department> departmentList = entityManager.createQuery("SELECT d FROM Department d", Department.class)
                .getResultList();
        for (int i = 0; i < departmentList.size(); i++) {
            System.out.println(String.format("%s %s", departmentList.get(i).getName(),
                    resultList.get(i)));
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
