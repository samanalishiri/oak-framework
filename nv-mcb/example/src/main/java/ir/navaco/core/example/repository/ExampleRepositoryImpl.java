package ir.navaco.core.example.repository;

import ir.navaco.core.example.domain.ExampleEntity;
import ir.navaco.core.repository.AbstractRepositoryImpl;
import org.springframework.stereotype.Repository;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
@Repository(ExampleRepositoryImpl.BEAN_NAME)
public class ExampleRepositoryImpl extends AbstractRepositoryImpl<Long, ExampleEntity> implements ExampleRepository {
    public static final String BEAN_NAME = "exampleRepositoryImpl";

}
