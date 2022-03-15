import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class _8_GetEmployeeWithProject {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        Scanner scan = new Scanner(System.in);
        Integer employeeId = Integer.parseInt(scan.nextLine());
        Employee employee = entityManager.createQuery("SELECT e FROM Employee e" +
                        " WHERE e.id = :id", Employee.class)
                .setParameter("id", employeeId)
                .getSingleResult();
        Iterator<Project> iterator = employee.getProjects().iterator();
        List<Project> projectList = new ArrayList<>();
        while (iterator.hasNext()) {
            Project current = iterator.next();
            projectList.add(current);
        }
        String projects = projectList.stream()
                .map(Project::getName)
                .sorted(String::compareTo)
                .collect(Collectors.joining(System.lineSeparator()));
        System.out.println(String.format("%s %s - %s%n%s", employee.getFirstName(), employee.getLastName(),
                employee.getJobTitle(), projects));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
