package ir.navaco.core.example.domain;

import ir.navaco.core.domain.AbstractEntity;
import ir.navaco.core.example.enums.ExampleType;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
@Entity
@Table(name = "EXAMPLE_TABLE")
@Audited
public class ExampleEntity extends AbstractEntity<Long> {

    private String name;

    private Integer no;

    private Date date;

    private ExampleType type;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @SequenceGenerator(name = "EXAMPLE_TABLE_GEN", sequenceName = "EXAMPLE_TABLE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXAMPLE_TABLE_GEN")
    @Override
    public Long getId() {
        return super.getId();
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name = "NO")
    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "OCCASION")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "EXAMPLE_TYPE")
    public ExampleType getType() {
        return type;
    }

    public void setType(ExampleType type) {
        this.type = type;
    }
}
