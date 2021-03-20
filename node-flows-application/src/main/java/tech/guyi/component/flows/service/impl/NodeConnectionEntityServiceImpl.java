package tech.guyi.component.flows.service.impl;

import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.guyi.component.flows.api.node.NodeConnection;
import tech.guyi.component.flows.db.entity.NodeConnectionEntity;
import tech.guyi.component.flows.db.repository.NodeConnectionEntityRepository;
import tech.guyi.component.flows.service.NodeConnectionEntityService;
import tech.guyi.component.flows.service.NodeEntityService;
import tech.guyi.component.flows.service.entry.NodeConnectionEntry;

import javax.annotation.Resource;

/**
 * @author guyi
 * @date 2021/3/19 22:21
 */
@Service
public class NodeConnectionEntityServiceImpl implements NodeConnectionEntityService {

    @Getter
    @Resource
    private NodeConnectionEntityRepository repository;

    @Resource
    private NodeEntityService nodeEntityService;

    @Override
    public Class<NodeConnectionEntity> entityClass() {
        return NodeConnectionEntity.class;
    }

    @Override
    @Transactional
    public NodeConnectionEntry save(String nodeId, NodeConnectionEntry entry){
        entry.setTarget(this.nodeEntityService.save(entry.getTarget()));
        NodeConnectionEntity connection = entry.to();
        connection.setNodeId(nodeId);
        connection.setTargetId(entry.getTarget().getId());
        entry.setId(this.autoSave(connection).getId());
        return entry;
    }

    @Override
    public NodeConnectionEntry getEntry(String id) {
        NodeConnectionEntity entity = this.getOne(id);
        NodeConnectionEntry entry = NodeConnectionEntry.from(entity);
        entry.setTarget(this.nodeEntityService.getEntry(entity.getTargetId()));
        return entry;
    }

    @Override
    public NodeConnection getConnection(NodeConnectionEntry entry) {
        NodeConnection connection = new NodeConnection();
        connection.setNode(this.nodeEntityService.getNode(entry.getTarget()));
        connection.setCondition(entry.getCondition());
        return connection;
    }

}
