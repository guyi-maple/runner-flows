package tech.guyi.component.flows.flow;

import lombok.Data;
import tech.guyi.component.flows.api.NodeFlowContext;
import tech.guyi.component.flows.api.flow.FlowConditionEngineType;
import tech.guyi.component.flows.api.node.Node;
import tech.guyi.component.flows.db.entry.NodeProperties;

/**
 * @author guyi
 * @date 2021/3/19 23:42
 */
@Data
public class Flow {

    private String id;
    private String name;
    private String detail;
    private Node header;
    private NodeProperties params;
    private FlowConditionEngineType engineType;

    private NodeFlowContext context;

    public void start(int poolSize) {
        this.context = new NodeFlowContext(poolSize,this.header, this.engineType);
        this.context.start(this.params);
    }

}
