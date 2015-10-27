/**
 *
 */
package info.jabara.sandbox.service.internal;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import info.jabara.sandbox.entity.EEmployee;
import info.jabara.sandbox.service.EmployeeService;
import jabara.general.ArgUtil;
import jabara.jpa.JpaDaoBase;

/**
 * @author jabaraster
 */
public class EmployeeServiceImpl extends JpaDaoBase implements EmployeeService {
    private static final long serialVersionUID = 5879950330161151707L;

    /**
     * @param pEntityManagerFactory -
     */
    @Inject
    public EmployeeServiceImpl(final PersistenceUnitProvider pEntityManagerFactory) {
        super(pEntityManagerFactory.get());
    }

    /**
     * @see info.jabara.sandbox.service.EmployeeService#delete(info.jabara.sandbox.entity.EEmployee)
     */
    @Transactional
    @Override
    public void delete(final EEmployee pDeleteTarget) {
        ArgUtil.checkNull(pDeleteTarget, "pDeleteTarget"); //$NON-NLS-1$
        final EntityManager em = getEntityManager();
        em.remove(em.merge(pDeleteTarget));
    }

    /**
     * @see info.jabara.sandbox.service.EmployeeService#getAll()
     */
    @Override
    public List<EEmployee> getAll() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<EEmployee> query = builder.createQuery(EEmployee.class);
        query.from(EEmployee.class);
        return em.createQuery(query).getResultList();
    }

    /**
     * @see info.jabara.sandbox.service.EmployeeService#insert(java.lang.String)
     */
    @Transactional
    @Override
    public EEmployee insert(final String pName) {
        final EEmployee newEmp = new EEmployee();
        newEmp.setName(pName);
        getEntityManager().persist(newEmp);
        return newEmp;
    }

}
