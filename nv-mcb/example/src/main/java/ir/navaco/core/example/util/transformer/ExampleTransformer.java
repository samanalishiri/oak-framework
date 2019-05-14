package ir.navaco.core.example.util.transformer;

import ir.navaco.core.example.domain.ExampleEntity;
import ir.navaco.core.example.enums.ExampleType;
import ir.navaco.core.example.vo.ExampleVo;
import ir.navaco.core.util.Transformer;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component(ExampleTransformer.BEAN_NAME)
public class ExampleTransformer extends Transformer<Long, ExampleEntity, ExampleVo> {

    public static final String BEAN_NAME = "ExampleTransformer";

    @Override
    public void transformEntityToModel(ExampleEntity input, ExampleVo output, int deep, String... fields) {
        output.setId(input.getId());
        output.setName(input.getName());
        output.setNo(input.getNo());
        output.setDate(input.getDate());
        Optional.ofNullable(input.getType())
                .ifPresent(inputExampleType -> output.setType(inputExampleType.name()));
    }

    @Override
    public void transformModelToEntity(ExampleVo input, ExampleEntity output, int deep, String... fields) {
        output.setId(input.getId());
        output.setName(input.getName());
        output.setNo(input.getNo());
        output.setDate(input.getDate());
        output.setType(ExampleType.valueOf(input.getType()));
    }
}
