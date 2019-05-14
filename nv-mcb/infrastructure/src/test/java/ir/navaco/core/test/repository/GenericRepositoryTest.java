package ir.navaco.core.test.repository;

import ir.navaco.core.domain.SearchRequest;
import ir.navaco.core.domain.SearchResponse;
import ir.navaco.core.repository.GenericRepository;
import ir.navaco.core.test.ParentTest;
import ir.navaco.core.test.domain.StudentEntity;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import static ir.navaco.core.domain.SearchRequest.DEFAULT;
import static ir.navaco.core.test.domain.StudentEntity.FIND_BY_FIRST_NAME;
import static ir.navaco.core.test.domain.StudentEntity.FIND_BY_ID_LOWER_THAN;
import static java.util.Collections.singletonMap;
import static org.junit.Assert.*;

public class GenericRepositoryTest extends ParentTest {

    @Autowired
    private GenericRepository genericRepository;

    public static final long NUMBER_OF_STUDENTS = 500L;

    @Override
    public void beforeMethod() {
        super.beforeMethod();
        setUpData(NUMBER_OF_STUDENTS);
        commitAndStartNewTransaction();
    }

    @Override
    public void afterMethod() {
        super.afterMethod();
        removeData();
        commitAndStartNewTransaction();
    }

    @Test
    public void persist_normallySaveAnObject_saveSuccessfully() {
        StudentEntity studentEntity = randomStudent(1000L);
        assertEquals(genericRepository.persist(StudentEntity.class, studentEntity).get(), studentEntity);
    }

    @Test
    public void countAll_addTwoEntityAndCount_countWouldBeSuccessful() {
        assertEquals(NUMBER_OF_STUDENTS, genericRepository.countAll(StudentEntity.class));
    }

    @Test
    public void countByNamedQuery() {
        assertEquals(100,
                genericRepository.countByNamedQuery(StudentEntity.class,
                        FIND_BY_ID_LOWER_THAN,
                        singletonMap("id", 101L)));
    }

    @Test
    public void testFindAll() {
        assertEquals(NUMBER_OF_STUDENTS,
                genericRepository.findAll(StudentEntity.class, SearchRequest.UNLIMITED).size());
    }

    @Test
    public void testFindByNamedQuery() {
        genericRepository.findByNamedQuery(StudentEntity.class, FIND_BY_ID_LOWER_THAN, singletonMap("id", 100L), DEFAULT)
                .forEach(s -> Assert.assertTrue(s.getId() < 100L));
    }

    @Test
    public void testSearchAll() {
        SearchResponse<StudentEntity> response = genericRepository.searchAll(StudentEntity.class, DEFAULT);
        assertEquals(NUMBER_OF_STUDENTS, response.getTotal());
        assertEquals(DEFAULT.getLimit(), response.getContent().size());
    }

    @Test
    public void testSearchByNamedQuery() {
        SearchResponse<StudentEntity> response =
                genericRepository.searchByNamedQuery(
                        StudentEntity.class, FIND_BY_ID_LOWER_THAN, singletonMap("id", 201L), DEFAULT);
        assertEquals(200L, response.getTotal());
        assertEquals(DEFAULT.getLimit(), response.getContent().size());
    }

    @Test
    public void testFindOne() {
        assertEquals(Optional.empty(), genericRepository.findOne(StudentEntity.class, -1L));
        assertNotNull(genericRepository.findOne(StudentEntity.class, 1L));
    }

    @Test
    public void testFindOneByNamedQuery() {
        assertNotNull(genericRepository.findOne(
                StudentEntity.class, FIND_BY_FIRST_NAME, singletonMap("firstName", "firstName#1")));
    }

    @Test
    public void modify() {
        doInTransaction(() -> {
            StudentEntity studentEntity = genericRepository.findOne(StudentEntity.class, 1L).orElse(null);
            studentEntity.setScore(randomStudent(studentEntity.getId()).getScore());
        });
        StudentEntity studentEntity = genericRepository.findOne(StudentEntity.class, 1L).orElse(null);
        assertNotEquals(studentEntity.getInsertDate(), studentEntity.getModifyDate());
        ;
    }

    private StudentEntity randomStudent(long id) {
        Random random = new Random();
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(id);
        studentEntity.setScore(random.nextDouble() * 20);
        studentEntity.setFirstName("firstName#" + id);
        studentEntity.setLastName("lastName#" + id);
        return studentEntity;
    }

    private void setUpData(Long numberOfStudents) {
        if (Objects.nonNull(genericRepository.getReference(StudentEntity.class, 1L))) {
            removeData();
        }
        for (int id = 1; id < numberOfStudents + 1; id++) {
            genericRepository.persist(StudentEntity.class, randomStudent(id));
        }
    }

    @Transactional
    public void removeData() {
        Query deleteStudentEntities = genericRepository.getEntityManager()
                .createQuery("delete from StudentEntity");
        deleteStudentEntities.executeUpdate();
    }
}