package tech.guyi.component.flows.api.runner;

import tech.guyi.component.flows.api.NodeFlowContext;

import java.util.Map;

/**
 * @author guyi
 * @date 2021/3/19 20:34
 */
public interface NodeRunner {

    void run(NodeFlowContext context, Object params, Map<String, Object> properties);

}
