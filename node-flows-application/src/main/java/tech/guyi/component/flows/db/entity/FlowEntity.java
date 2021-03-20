package tech.guyi.component.flows.db.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.guyi.component.flows.api.flow.FlowConditionEngineType;
import tech.guyi.component.flows.db.entry.NodeProperties;
import tech.guyi.web.quick.service.entity.QuickUuidEntity;

import javax.persistence.Entity;

/**
 * @author guyi
 * @date 2021/3/19 22:34
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class FlowEntity extends QuickUuidEntity {

    private String name;
    private String detail;
    private String header;
    private NodeProperties params;
    private FlowConditionEngineType engineType;

}
