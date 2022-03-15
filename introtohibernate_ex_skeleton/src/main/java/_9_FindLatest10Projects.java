import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.List;

public class _9_FindLatest10Projects {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Project> projectList = entityManager.createQuery("SELECT p FROM Project p" +
                        " ORDER BY p.startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultList();
        projectList.stream()
                .sorted((Comparator.comparing(Project::getName)))
                .forEach(p -> {
                    System.out.println(String.format("Project name:%s%n" +
                                    "       Project Description:%s%n" +
                                    "       Project Start Date:%s%n" +
                                    "       Project End Date:%s"
                            , p.getName(), p.getDescription(),
                            p.getStartDate(), p.getEndDate()));
                });
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
