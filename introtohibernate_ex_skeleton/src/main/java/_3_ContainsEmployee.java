import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Scanner;

public class _3_ContainsEmployee {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        Scanner scan = new Scanner(System.in);
        String[] searchEmployee = scan.nextLine().split("\\s+");
        Long count = entityManager.createQuery("SELECT COUNT(e) FROM Employee e" +
                                " WHERE e.firstName = :first_name" +
                                " AND e.lastName = :last_name",
                        Long.class)
                .setParameter("first_name", searchEmployee[0])
                .setParameter("last_name", searchEmployee[1])
                .getSingleResult();
        if (count > 0) {
            System.out.println("Yes");
        } else  {
            System.out.println("No");
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
