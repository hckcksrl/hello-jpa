package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * allocationSize은 기본으로 50으로 설정되어있는데 시퀀스 데이터를 2번 불러오는데
 * 첫번째 불러올때는 지금의 시퀀스값, 지금의 시퀀스값 +50이다.
 * 이것은 미리 50개를 가져와서 메모리에 저장하고 메모리에 저장된 값을 사용하는것이다.
 * 불러온 값을 다 사용한다면 다시 시퀀스값, 시퀀스의 +50값을 불러온다.
 * 이렇게하면 여러개의 서버를 사용한다해도 동시성문제가 발생하지 않는다.
 */
//@SequenceGenerator(
//        name = "MEMBER_SEQ_GENERATOR",
//        sequenceName = "MEMBER_SEQ",
//        initialValue = 1, allocationSize = 50)
@Entity
public class Member extends BaseEntity{

    /**
     * IDENTITY인 경우 트랜잭션 할때 쿼리가 생성되는것이 아니라
     * persist할때 쿼리가 생성
     */
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,
//            generator = "MEMBER_SEQ_GENERATOR" )
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createData;

    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
    private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Member() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreateData() {
        return createData;
    }

    public void setCreateData(Date createData) {
        this.createData = createData;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

/**
 * @Column = 컬럼 매핑
 * @Temporal = 날짜 타입 매핑
 * @Enumerated = enum타입 매핑
 * @Lob = BLOB, CLOB 매핑
 * @Transient 특정 필드 매핑 무시
 */
