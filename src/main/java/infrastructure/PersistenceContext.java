package infrastructure;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * As usual there many different ways to achieve architectural goals.
 * In this case I decided to provide a bean for creating the persistence
 * context to my application. I want to inject an {@link EntityManager} object
 * whenever I want. So I have to provide a data structure, which configures the
 * entityManger in a public accessible manner.
 *
 * Accordingly to this requirement I have to supply a bean on demand,
 * produces the entityManager on demand as well.
 *
 * Doing so I can use @Inject at fields of type {@link EntityManager} in all
 * repositories I want to. Reminder: To use cdi features I need the targeted class to be
 * a scoped bean. It has to be a bean (@Named) associated with a scope (@RequestScoped i.e.).
 *
 * For this small use case my approach works well, but I am not certainly sure, whether it is
 * best practice in every sense of application engineering.
 */
@RequestScoped
class PersistenceContext {

    /**
     * The annotation let the java virtual machine know, where to get objects of
     * type {@link EntityManager} from. To enable the jvm to call this method on
     * runtime I embed it in a bean class.
     *
     * @return          An instance of entity manager to the given persistence unit.
     */
    @Produces
    public EntityManager createEntityManager() {

        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("javaee");

        return entityManagerFactory.createEntityManager();
    }

    /**
     * When I have a producer method to an entity manager I need as destructive method as well.
     * This I mark with the @Disposes annotation.
     *
     * @param entityManager         The entityManger I want to be closed.
     */
    public void closeEntityManager(@Disposes EntityManager entityManager) {
        entityManager.close();
    }
}
