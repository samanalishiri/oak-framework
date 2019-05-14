package ir.navaco.core.domain;

import ir.navaco.core.util.SecurityUtils;
import org.hibernate.envers.RevisionListener;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public class AuditRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        AuditRevisionEntity.class
                .cast(revisionEntity)
                .setUsername(SecurityUtils.getCurrentUsername());
    }

}
