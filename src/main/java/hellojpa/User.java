package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {

    @Id
    private Long id;

    @Column(unique = true, length = 10)
    private String name;
    /**
     * 데이터베이스 스키마 자동생성의 경우 운영서버, 테스트서버는 아무것도 사용안하는것이 좋다
     * 스테이징 서버에서도 안쓰는것이 좋은데 쓰면 validate만 쓰는것이 좋다
     * 테스트서버, 개발서버도 가급적이면 스크립트르 짜서 적용하는것 추 (현직)
     */
    public int age;

    public User() {}

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
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
