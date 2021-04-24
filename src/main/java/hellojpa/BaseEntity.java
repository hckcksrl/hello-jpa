package hellojpa;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 여러테이블에 공통적으로 사용할 정보를 넣을경우 공통적인 컬럼들을 BaseEntity로 만든다음
 * MappedSuperClass를 적용시키면 상속받은 모든테이블에 BaseEntity에 정의한 값들이 생성됨
 * MappedSuperClass는 조회를 할수가 없다.
 * 추상클래스로 생성하는것을 권장
 */
@MappedSuperclass
public abstract class BaseEntity {

    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
