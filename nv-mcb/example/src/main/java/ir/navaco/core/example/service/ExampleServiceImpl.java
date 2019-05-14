package ir.navaco.core.example.service;

import ir.navaco.core.example.domain.ExampleEntity;
import ir.navaco.core.example.repository.ExampleRepository;
import ir.navaco.core.example.repository.ExampleRepositoryImpl;
import ir.navaco.core.example.util.transformer.ExampleTransformer;
import ir.navaco.core.example.vo.ExampleVo;
import ir.navaco.core.service.AbstractBusinessService;
import ir.navaco.core.util.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */

@Service(ExampleServiceImpl.BEAN_NAME)
public class ExampleServiceImpl extends AbstractBusinessService<Long, ExampleVo, ExampleEntity, ExampleRepository>
        implements ExampleService {

    public static final String BEAN_NAME = "exampleServiceImpl";

    @Autowired
    public ExampleServiceImpl(@Qualifier(ExampleRepositoryImpl.BEAN_NAME) ExampleRepository repository,
                              @Qualifier(ExampleTransformer.BEAN_NAME) Transformer<Long, ExampleEntity, ExampleVo> transformer) {
        super(repository, transformer);
    }
}
