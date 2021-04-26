package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    /**
     * Cascade는 조심히 사용해야한다. 애매하면 사용하지 않는다.
     * 도메인 주도 설계(DDD)의 Aggregate Root와 어울린다.
     * * 완전 개인 소유인 경우에 사용할 수 있다. *
     * ex) childList는 Parent에만 연관이 있다. 만약에 다른 Member, Item등 여러 엔티티와 연관이 있으면 사용하면 안된다.
     * 고아객체는 부모 엔티티와 연관관계가 끊어진 자식 엔티티이다.
     * 고아객체제거 기능을 활성화하면 Cascade Remove처럼 동작한다.
     *  * 참조하는곳이 하나일때, 특정 엔티티가 개인소유할때만 사용해야한다.*
     * @OneToMany, OneToOne만 가능
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Child> childList = new ArrayList<>();

    public void addChild(Child child) {
        childList.add(child);
        child.setParent(this);
    }

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }

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
}
