/**
 * 
 */
package info.jabara.sandbox.service.internal;

import info.jabara.sandbox.Environment;

import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * @author jabaraster
 */
@Singleton
public class PersistenceUnitProvider implements Provider<EntityManagerFactory> {

    @PersistenceUnit(unitName = Environment.APPLICATION_NAME)
    EntityManagerFactory entityManagerFactory;

    /**
     * @see javax.inject.Provider#get()
     */
    @Override
    public EntityManagerFactory get() {
        return this.entityManagerFactory;
    }
}
