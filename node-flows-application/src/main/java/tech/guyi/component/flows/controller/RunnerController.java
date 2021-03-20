package tech.guyi.component.flows.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.guyi.component.flows.service.RunnerService;
import tech.guyi.component.flows.service.entry.RunnerEntry;
import tech.guyi.web.quick.core.controller.ResponseContent;
import tech.guyi.web.quick.core.controller.ResponseEntities;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author guyi
 * @date 2021/3/20 16:18
 */
@RestController
@RequestMapping("runner")
public class RunnerController {

    @Resource
    private RunnerService service;

    @GetMapping
    public ResponseEntity<ResponseContent<List<RunnerEntry>>> all(){
        return ResponseEntities.ok(this.service.all());
    }

}
