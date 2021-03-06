import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by demian on 2016-11-10.
 *
 * http://www.vogella.com/tutorials/JavaPersistenceAPI/article.html
 */
public class Main {
    private static final String PERSISTENCE_UNIT_NAME = "todos";
    private static EntityManagerFactory factory;

    public static void main(String[] args) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        // Output current rows
        Query q = em.createQuery("select t from Todo t");
        List<Todo> todoList = q.getResultList();
        for (Todo todo : todoList) {
            System.out.println(todo);
        }
        System.out.println("Size: " + todoList.size());

        // Create a new one
        em.getTransaction().begin();
        Todo todo = new Todo();
        todo.setSummary("Summary");
        todo.setDescription("Description");
        em.persist(todo);
        em.getTransaction().commit();

        em.close();
    }

}
