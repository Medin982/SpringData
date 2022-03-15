import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _2_ChangeCasing {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("UPDATE Town as t " +
                "SET t.name = UPPER(t.name) " +
                "WHERE LENGTH(t.name) >= 5").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
