package tech.guyi.component.flows.endpoint.mid;

import tech.guyi.component.flows.api.NodeFlowContext;
import tech.guyi.component.flows.api.runner.NodeRunner;

import java.util.Map;
import java.util.Optional;

/**
 * @author guyi
 * @date 2021/3/19 23:09
 */
public class ByteToStringRunner implements NodeRunner {

    @Override
    public void run(NodeFlowContext context, Object params, Map<String, Object> properties) {
        if (params == null){
            context.next();
            return;
        }

        context.next(
                Optional.of(params)
                        .filter(p -> p instanceof byte[])
                        .map(p -> (byte[]) p)
                        .map(String::new)
                        .orElse(params.toString())
        );
    }

}
