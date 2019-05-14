package ir.navaco.core.test.domain;

import ir.navaco.core.domain.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

import static ir.navaco.core.test.domain.StudentEntity.TABLE_NAME;

/**
 * @author Hossein Amiri Parian - parian66@gmail.com
 * Date 5/7/2018.
 */
@Entity
@Table(name = TABLE_NAME)
@NamedQueries(value = {
        @NamedQuery(name = StudentEntity.FIND_BY_FIRST_NAME, query = "from StudentEntity where firstName = :firstName"),
        @NamedQuery(name = StudentEntity.FIND_BY_ID_LOWER_THAN, query = "from StudentEntity where id < :id")
})
public class StudentEntity extends AbstractEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final String TABLE_NAME = "Student";
    private static final String SEQUENCE_NAME = TABLE_NAME + "_SEQ";
    private static final String SEQUENCE_GENERATOR_NAME = TABLE_NAME + "_SEQUENCE";
    public static final String FIND_BY_FIRST_NAME = TABLE_NAME + ".findByFirstName";
    public static final String FIND_BY_ID_LOWER_THAN = TABLE_NAME + ".findByIdLowerThan";

    @Id
    @SequenceGenerator(name = SEQUENCE_GENERATOR_NAME, sequenceName = SEQUENCE_NAME, initialValue = 0, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR_NAME)
    @Column(length = 12)
    private Long id;

    private String firstName;

    private String lastName;

    private Double score;

    private Boolean active;

    private Date registerDate;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}
