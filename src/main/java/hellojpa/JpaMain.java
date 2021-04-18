package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        /**
         * EntityManagerFactory는 Application 로딩시점에 하나만 생성
         * 실제로 DB커넥션을 얻어서 쿼리를 생성하고 종료하는 트랜잭션단위를 할때마다 EntityManager를 생성
         */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        /**
         * EntityManager는 생성될때 DB와 커넥션이 생성됨
         */
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//
//            em.persist(member);

            /*
             * 값만 바뀌었는데 업데이트 쿼리가 발생하는 이유는 JPA가 엔티티 관리를하고 트랜잭션이
             * 커밋하는 시점에서 변경된 사항을 체크를하고 변경이 되었으면 업데이트 쿼리를 만들어서 요청하고
             * 트랜잭션이 커밋이됨
             * Member findMember = em.find(Member.class, 1L);
             * findMember.setName("HelloJPA");
             */

            /**
             *  select 문에서 Member는 테이블의 Member가 아닌 객체 Member이다.
             *  JPQL을 실행하면 flush가 자동호출
             */
            List<User> result = em.createQuery("select m from User as m", User.class)
                    .getResultList();

            for (User user : result) {
                System.out.println("user.name = " + user.getName());
            }

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            /**
             * DB 커넥션을 반환
             */
            em.close();
        }

        emf.close();
    }
}
