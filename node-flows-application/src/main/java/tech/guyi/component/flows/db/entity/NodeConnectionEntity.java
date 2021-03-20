package tech.guyi.component.flows.db.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.guyi.web.quick.service.entity.QuickUuidEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author guyi
 * @date 2021/3/19 22:20
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class NodeConnectionEntity extends QuickUuidEntity {

    private String nodeId;
    private String targetId;
    @Column(columnDefinition = "mediumtext")
    private String condition;

}
