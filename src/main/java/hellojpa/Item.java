package hellojpa;

import javax.persistence.*;

/**
 * @Inheritance 기본전략은 Single_Table이다.
 * @Inheritance(strategy = InheritanceType.JOINED) 을 하면 조인전략을 써서
 * 한개의 슈퍼타입과 3개의 서브타입이 생긴다.
 * Single_Table전략일경우 DTYPE이 필수로 생성이된다.
 * JOINED 전략일경우 @DiscriminatorColumn를 해줘야 DTYPE이 생성된다.
 * TABLE_PER_CLASS 전략일경우 abstract(추상클래스)로 만들어야 한다. 데이터를 꺼내올때 UNION으로 연관된 모든테이블을 조인한다.
 * 조인전략이 정석
 * TABLE_PER_CLASS전략은 쓰면안됨
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
