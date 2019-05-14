package ir.navaco.core.example.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import ir.navaco.core.example.enums.ExampleType;
import ir.navaco.core.validator.EnumValidator;
import ir.navaco.core.vo.AbstractVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
@Component(ExampleVo.BEAN_NAME)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ExampleVo extends AbstractVo<Long> {

    public static final String BEAN_NAME = "exampleVo";

    @NotBlank(message = "error.validation.notBlank")
    private String name;

    @Min(value = 0L, message = "error.validation.min")
    @Max(value = 100L, message = "error.validation.max")
    private Integer no;

    @NotNull(message = "error.validation.notNull")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    @EnumValidator(clazz = ExampleType.class, message = "error.validation.enumNotFount")
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
