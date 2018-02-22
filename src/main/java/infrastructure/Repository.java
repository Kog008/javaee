package infrastructure;

import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

class Repository {

    private EntityManagerFactory entityManagerFactory;

    public EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
