package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMainFour {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("hello");
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("hello2");
            em.persist(member2);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            Member findMember2 = em.getReference(Member.class, member2.getId());
            System.out.println("findMember2.getClass() = " + findMember2.getClass());

            em.detach(findMember2);

            System.out.println("findMember2.getClass() = " + findMember2.getClass());

            System.out.println("findMember.id = " + findMember2.getId());
            System.out.println("findMember.username = " + findMember2.getUsername());
            /**
             * 프록시 객체는 처음사용할때 한번만 초기화되고 초기화할때 프록시 객체가 실제 엔티티로 바뀌는게 아니고
             * 프록시객체를 통해 실제 엔티티에 접근가능하다.
             * 이때문에 프록시객체는 타입체크시 == 이 아니라 instace of 를 사용해야 한다.
             * 영속성 컨텍스트에 찾는 엔티티가 이미 있으면 getReference를 사용해도 실제 엔티티가 반환됨
             */

            logic(findMember, findMember2);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

    }

    private static void logic(Member findMember, Member findMember2) {
        System.out.println("findMember == findMember2: " + (findMember instanceof Member));
        System.out.println("findMember == findMember2: " + (findMember2 instanceof Member));
        System.out.println("findMember == findMember2: " + (findMember.getClass() == findMember2.getClass()));
    }
}

