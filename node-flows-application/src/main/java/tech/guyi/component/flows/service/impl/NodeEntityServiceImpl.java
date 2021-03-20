package tech.guyi.component.flows.service.impl;

import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.guyi.component.flows.NodeRunnerFactory;
import tech.guyi.component.flows.api.node.Node;
import tech.guyi.component.flows.db.entity.NodeConnectionEntity;
import tech.guyi.component.flows.db.entity.NodeEntity;
import tech.guyi.component.flows.db.repository.NodeEntityRepository;
import tech.guyi.component.flows.service.NodeConnectionEntityService;
import tech.guyi.component.flows.service.NodeEntityService;
import tech.guyi.component.flows.service.entry.NodeEntry;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author guyi
 * @date 2021/3/19 22:16
 */
@Service
public class NodeEntityServiceImpl implements NodeEntityService {

    @Getter
    @Resource
    private NodeEntityRepository repository;

    @Resource
    private NodeRunnerFactory runnerFactory;

    @Resource
    private NodeConnectionEntityService connectionEntityService;

    @Override
    public Class<NodeEntity> entityClass() {
        return NodeEntity.class;
    }

    @Override
    @Transactional
    public NodeEntry save(NodeEntry entry){
        NodeEntity node = this.autoSave(entry.to());
        Optional.ofNullable(entry.getConnections())
                .map(connections -> connections.stream()
                        .map(conn -> this.connectionEntityService.save(node.getId(), conn))
                        .collect(Collectors.toList()))
                .ifPresent(entry::setConnections);
        entry.setId(node.getId());
        return entry;
    }

    @Override
    public NodeEntry getEntry(String id) {
        NodeEntry entry = NodeEntry.from(this.getOne(id));
        entry.setConnections(
                this.connectionEntityService.findByNodeId(entry.getId())
                        .stream()
                        .map(NodeConnectionEntity::getId)
                        .map(this.connectionEntityService::getEntry)
                        .collect(Collectors.toList())
        );
        return entry;
    }

    @Override
    public Node getNode(NodeEntry entry) {
        Node node = new Node();
        node.setProperties(entry.getProperties());
        node.setRunner(this.runnerFactory.create(entry.getRunner())
                        .orElseThrow(() -> new RuntimeException("执行器不存在")));
        Optional.ofNullable(entry.getConnections())
                .ifPresent(connections ->
                        node.setConnections(connections.stream()
                                .map(this.connectionEntityService::getConnection)
                                .collect(Collectors.toList())));
        return node;
    }


}
