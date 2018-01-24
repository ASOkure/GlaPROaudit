package uk.org.bsped.dao;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class AbstractExtendDao {
    public static final int DEFAULT_FIRST_RESULT_INDEX = 0;

    public abstract EntityManager getEntityManager();

    @Transactional
    public Query createNamedQuery(String queryName, Integer firstResult, Integer maxResults, Object... parameters) {
        Query query = getEntityManager().createNamedQuery(queryName);
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                query.setParameter(i + 1, parameters[i]);
            }
        }

        query.setFirstResult(firstResult == null || firstResult < 0 ? DEFAULT_FIRST_RESULT_INDEX : firstResult);
        if (maxResults != null && maxResults > 0) {
            query.setMaxResults(maxResults);
        }
        return query;
    }
}
