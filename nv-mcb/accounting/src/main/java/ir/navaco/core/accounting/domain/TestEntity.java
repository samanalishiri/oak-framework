package ir.navaco.core.accounting.domain;

import ir.navaco.core.domain.AbstractEntity;
import org.hibernate.envers.Audited;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
@Entity
@Table(name = "TEST_TABLE")
@Audited
@Access(value = AccessType.PROPERTY)
public class TestEntity extends AbstractEntity<Long> {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @SequenceGenerator(name = "TEST_TABLE_GEN", sequenceName = "TEST_TABLE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEST_TABLE_GEN")
    @Override
    public Long getId() {
        return super.getId();
    }

}
