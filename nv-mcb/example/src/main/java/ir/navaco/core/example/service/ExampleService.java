package ir.navaco.core.example.service;

import ir.navaco.core.example.vo.ExampleVo;
import ir.navaco.core.service.CrudService;
import ir.navaco.core.service.SearchService;

public interface ExampleService extends CrudService<Long, ExampleVo>, SearchService<Long, ExampleVo> {
}
