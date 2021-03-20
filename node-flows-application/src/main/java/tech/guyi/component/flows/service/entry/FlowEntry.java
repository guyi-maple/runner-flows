package tech.guyi.component.flows.service.entry;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import tech.guyi.component.flows.api.flow.FlowConditionEngineType;
import tech.guyi.component.flows.db.entity.FlowEntity;
import tech.guyi.component.flows.db.entry.NodeProperties;

/**
 * @author guyi
 * @date 2021/3/19 22:36
 */
@Data
public class FlowEntry {

    private String id;
    private String name;
    private String detail;
    private NodeEntry header;
    private NodeProperties params;
    private FlowConditionEngineType engineType;

    public FlowEntity to(){
        FlowEntity entity = new FlowEntity();
        BeanUtils.copyProperties(this,entity);
        return entity;
    }

    public static FlowEntry from(FlowEntity entity){
        FlowEntry entry = new FlowEntry();
        BeanUtils.copyProperties(entity, entry);
        return entry;
    }

}
