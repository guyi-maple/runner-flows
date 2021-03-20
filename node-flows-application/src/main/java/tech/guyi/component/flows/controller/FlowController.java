package tech.guyi.component.flows.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.guyi.component.flows.service.FlowEntityService;
import tech.guyi.component.flows.service.entry.FlowEntry;
import tech.guyi.web.quick.core.controller.ResponseContent;
import tech.guyi.web.quick.core.controller.ResponseEntities;

import javax.annotation.Resource;

/**
 * @author guyi
 * @date 2021/3/19 22:57
 */
@RestController
@RequestMapping("flow")
public class FlowController {

    @Resource
    private FlowEntityService service;

    @PostMapping
    public ResponseEntity<ResponseContent<FlowEntry>> create(@RequestBody FlowEntry entry){
        return ResponseEntities.ok(this.service.save(entry));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseContent<FlowEntry>> get(@PathVariable("id") String id){
        return this.service.findById(id)
                .map(this.service::getEntry)
                .map(ResponseEntities::ok)
                .orElseGet(ResponseEntities::_404);
    }

    @GetMapping("start/{id}")
    public ResponseEntity<ResponseContent<Boolean>> start(@PathVariable("id") String id){
        return this.service.findById(id)
                .map(this.service::getEntry)
                .map(this.service::getFlow)
                .map(flow -> {
                    flow.start(10);
                    return ResponseEntities.ok(true);
                })
                .orElseGet(ResponseEntities::_404);
    }

}
