package tech.guyi.component.flows.endpoint.output;

import tech.guyi.component.flows.api.NodeFlowContext;
import tech.guyi.component.flows.api.runner.NodeRunner;

import java.util.Map;

/**
 * @author guyi
 * @date 2021/3/16 21:50
 */
public class ConsoleRunner implements NodeRunner {

    @Override
    public void run(NodeFlowContext context, Object params, Map<String, Object> properties) {
        System.out.printf("[%s] %s%n", params.getClass(), params);
    }

}
