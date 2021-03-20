package tech.guyi.component.flows.db.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.guyi.component.flows.db.entry.NodePosition;
import tech.guyi.component.flows.db.entry.NodeProperties;
import tech.guyi.web.quick.service.entity.QuickUuidEntity;

import javax.persistence.Entity;

/**
 * @author guyi
 * @date 2021/3/19 21:27
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class NodeEntity extends QuickUuidEntity {

    private String name;
    private String detail;
    private String runner;
    private NodePosition position;
    private NodeProperties properties;

}
