package tech.guyi.component.flows.endpoint.mid;

import tech.guyi.component.flows.api.NodeFlowContext;
import tech.guyi.component.flows.api.runner.NodeRunner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Map;
import java.util.Optional;

/**
 * @author guyi
 * @date 2021/3/20 00:20
 */
public class JavaScriptRunner implements NodeRunner {

    private static final ScriptEngineManager engineManager = new ScriptEngineManager();

    private static final String FUNCTION_KEY = "function";

    @Override
    public void run(NodeFlowContext context, Object params, Map<String, Object> properties) {
        ScriptEngine engine = engineManager.getEngineByName("js");
        engine.put("params", params);
        engine.put("properties", properties);
        Optional.ofNullable(properties.get(FUNCTION_KEY))
                .map(Object::toString)
                .ifPresent(function -> {
                    try {
                        context.next(engine.eval(function));
                    } catch (ScriptException e) {
                        e.printStackTrace();
                    }
                });
    }

}
