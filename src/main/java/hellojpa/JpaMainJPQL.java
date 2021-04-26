package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMainJPQL {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /**
             * Criteria 실무에서는 안쓴다. 알고만 있으면 됨
             * QueryDSL 사용하는것을 권장한다.
             */
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<CollectionMember> query = cb.createQuery(CollectionMember.class);

            Root<CollectionMember> m = query.from(CollectionMember.class);

            CriteriaQuery<CollectionMember> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
            List<CollectionMember> resultList = em.createQuery(cq)
                    .getResultList();


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

    }
}
