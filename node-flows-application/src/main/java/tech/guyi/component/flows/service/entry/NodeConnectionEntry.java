package tech.guyi.component.flows.service.entry;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import tech.guyi.component.flows.db.entity.NodeConnectionEntity;

/**
 * @author guyi
 * @date 2021/3/19 22:43
 */
@Data
public class NodeConnectionEntry {

    private String id;
    private NodeEntry target;
    private String condition;

    public NodeConnectionEntity to(){
        NodeConnectionEntity entity = new NodeConnectionEntity();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }

    public static NodeConnectionEntry from(NodeConnectionEntity entity){
        NodeConnectionEntry entry = new NodeConnectionEntry();
        BeanUtils.copyProperties(entity, entry);
        return entry;
    }

}
