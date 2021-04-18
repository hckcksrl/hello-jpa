package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMainTwo {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /**
             * 비영속 => 영속성 컨텍스트와 관계가 없는 새로운 상태
             * 영속 => 영속성 컨텍스트에 관리되는 상태
             * 준영속 => 영속성 컨텍스트에 관리되다가 분리된 상태
             * 삭제 => 삭제된 상태
             */

            /**
             * 영속석 컨텍스트의 이점
             * 1차 캐시기능
             * 동일성 보장
             * 트랜잭션을 지원하는 쓰기 지연
             * 변경 감지
             * 지연 로딩
             */

//            // 비영속상태
//            Member member = new Member();
//            member.setId(100L);
//            member.setName("HelloJPA");
//
//            // 영속상태
//            em.persist(member);
//
//            Member findMember = em.find(Member.class, 100L);
//
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());

//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//            em.persist(member1);
//            em.persist(member2);

            /**
             * 객체 데이터를 변경하면 persist를 해주면 안된다.
             * 내부적으로 1차캐시저장소에서 변경된 객체와 스냅샷한 객체를 비교해서 자체적으로 쿼리를 만들어서
             * 쓰기지연 저장소에 저장을하고 트랜잭션 커밋을 할때 쿼리가 발생
             */
            User user = em.find(User.class, 150L);
            user.setName("222222");

            /**
             * 영속성 컨텍스트를 비우지않음
             * 영속성 컨텍스트의 변경내용을 데이터베이스에 동기화
             * 트랜잭션 작업단위가 중요 ( 커밋직전에만 하면됨 )
             */
//            em.flush();

            /**
             * 준영속 상태
             * 영속성 컨택스트에서 관리를 안함
             * 트랜잭션에서 커밋을 할때 이벤트가 발생안함
             * 특정 엔티티 준영속상태 => EntityManager.detach(Entity)
             * 모든 엔티티 준영속상태 => EntityManager.clear()
             * 영속컨텍스트 종료 => EntityManager.close()
             */
//            em.detach(member);

            System.out.println("================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

    }
}
