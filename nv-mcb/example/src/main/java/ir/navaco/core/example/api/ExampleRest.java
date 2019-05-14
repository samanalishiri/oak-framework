package ir.navaco.core.example.api;

import io.swagger.annotations.Api;
import ir.navaco.core.api.AbstractRestfulApi;
import ir.navaco.core.example.service.ExampleService;
import ir.navaco.core.example.service.ExampleServiceImpl;
import ir.navaco.core.example.vo.ExampleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This Restful web service is only example
 *
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
@Api(value = "example/api/v1", description = "", tags = {"Example API"})
@RestController
@RequestMapping("api/v1")
public class ExampleRest extends AbstractRestfulApi<Long, ExampleVo, ExampleService> {

    private Logger logger = LoggerFactory.getLogger("example-rest");

    @Autowired
    public ExampleRest(@Qualifier(ExampleServiceImpl.BEAN_NAME) ExampleService service) {
        super(service);
    }
}
