package tech.guyi.component.flows.service.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;
import tech.guyi.component.flows.db.entity.NodeEntity;

import java.util.List;

/**
 * @author guyi
 * @date 2021/3/19 22:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NodeEntry extends NodeEntity {

    private List<NodeConnectionEntry> connections;

    public NodeEntity to(){
        NodeEntity entity = new NodeEntity();
        BeanUtils.copyProperties(this,entity);
        return entity;
    }

    public static NodeEntry from(NodeEntity entity){
        NodeEntry entry = new NodeEntry();
        BeanUtils.copyProperties(entity, entry);
        return entry;
    }

}
