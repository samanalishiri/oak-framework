package ir.navaco.core.domain;

import ir.navaco.core.util.SecurityUtils;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public class AuditingEntityListener {

    @PrePersist
    public void touchForCreate(Object target) {
        AbstractEntity entity = AbstractEntity.class.cast(target);
        entity.setInsertUserId(SecurityUtils.getCurrentUserId());
        entity.setInsertDate(new Date());
        entity.setInsertOrganizationId(SecurityUtils.getCurrentOrganizationId());
    }

    @PreUpdate
    public void touchForUpdate(Object target) {
        AbstractEntity entity = AbstractEntity.class.cast(target);
        entity.setModifyUserId(SecurityUtils.getCurrentUserId());
        entity.setModifyDate(new Date());
        entity.setModifyOrganizationId(SecurityUtils.getCurrentOrganizationId());
    }
}