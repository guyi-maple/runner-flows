package tech.guyi.component.flows.service;

import tech.guyi.component.flows.db.entity.FlowEntity;
import tech.guyi.component.flows.flow.Flow;
import tech.guyi.component.flows.service.entry.FlowEntry;
import tech.guyi.web.quick.service.service.QuickService;

/**
 * @author guyi
 * @date 2021/3/19 22:38
 */
public interface FlowEntityService extends QuickService<FlowEntity, String> {

    FlowEntry save(FlowEntry entry);

    default FlowEntry getEntry(String id) {
        return this.getEntry(this.getOne(id));
    }

    FlowEntry getEntry(FlowEntity entity);

    Flow getFlow(FlowEntry entry);

}
