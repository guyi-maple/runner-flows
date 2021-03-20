package tech.guyi.component.flows.service;

import tech.guyi.component.flows.api.node.Node;
import tech.guyi.component.flows.db.entity.NodeEntity;
import tech.guyi.component.flows.service.entry.NodeEntry;
import tech.guyi.web.quick.service.service.QuickService;

/**
 * @author guyi
 * @date 2021/3/19 22:16
 */
public interface NodeEntityService extends QuickService<NodeEntity, String> {

    NodeEntry save(NodeEntry entry);

    NodeEntry getEntry(String id);

    Node getNode(NodeEntry entry);

}
