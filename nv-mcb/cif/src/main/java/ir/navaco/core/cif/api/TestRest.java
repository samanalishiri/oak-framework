package ir.navaco.core.cif.api;

import ir.navaco.core.cif.vo.TestVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
@RestController
public class TestRest {

    private Logger logger = LoggerFactory.getLogger("test-rest");

    @RequestMapping("/test-rest")
    public TestVo hello() {
        logger.info("test-rest");

        TestVo dto = new TestVo();
        dto.setMessage("Test is Successful");

        return dto;
    }
}
