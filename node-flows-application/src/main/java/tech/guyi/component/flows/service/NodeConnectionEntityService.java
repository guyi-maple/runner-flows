package tech.guyi.component.flows.service;

import tech.guyi.component.flows.api.node.NodeConnection;
import tech.guyi.component.flows.db.entity.NodeConnectionEntity;
import tech.guyi.component.flows.service.entry.NodeConnectionEntry;
import tech.guyi.web.quick.service.service.QuickService;

import java.util.List;

/**
 * @author guyi
 * @date 2021/3/19 22:21
 */
public interface NodeConnectionEntityService extends QuickService<NodeConnectionEntity,String> {

    default List<NodeConnectionEntity> findByNodeId(String nodeId){
        return this.findAll(((root, query, builder) ->
                builder.and(builder.equal(root.get("nodeId"), nodeId))));
    }

    NodeConnectionEntry save(String nodeId, NodeConnectionEntry entry);

    NodeConnectionEntry getEntry(String id);

    NodeConnection getConnection(NodeConnectionEntry entry);

}
