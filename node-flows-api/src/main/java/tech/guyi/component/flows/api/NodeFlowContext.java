package tech.guyi.component.flows.api;

import tech.guyi.component.flows.api.flow.FlowConditionEngineType;
import tech.guyi.component.flows.api.node.Node;
import tech.guyi.component.flows.api.node.NodeConnection;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Consumer;

/**
 * 执行流上下文
 * @author guyi
 * @date 2021/3/16 20:24
 */
public class NodeFlowContext extends HashMap<String,Object> {

    private static final ScriptEngineManager engineManager = new ScriptEngineManager();

    private final Node node;
    private final ScheduledExecutorService service;
    private final FlowConditionEngineType engineType;

    public NodeFlowContext(Node node,  FlowConditionEngineType engineType) {
        this(5, node, engineType);
    }

    public NodeFlowContext(int poolSize, Node node, FlowConditionEngineType engineType) {
        this.node = node;
        this.engineType = engineType;
        this.service = Executors.newScheduledThreadPool(poolSize);
    }

    private NodeFlowContext(ScheduledExecutorService service, Node node, FlowConditionEngineType engineType) {
        this.node = node;
        this.engineType = engineType;
        this.service = service;
    }

    public ScheduledExecutorService service() {
        return service;
    }

    public NodeFlowContext copy(Node node){
        return new NodeFlowContext(this.service, node, this.engineType);
    }

    public void start(Object params){
        this.service.submit(() -> this.node.getRunner().run(
                this,
                params,
                this.node.getProperties()
        ));
    }

    /**
     * 判断条件是否满足
     * @param condition 条件
     * @param params 入参
     * @return 是否满足
     */
    private boolean conditionExecute(String condition, Object params){
        ScriptEngine engine = engineManager.getEngineByName(this.engineType.getValue());
        engine.put("params", params);
        try {
            return Boolean.parseBoolean(engine.eval(condition).toString());
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void onStop(Consumer<NodeFlowContext> consumer){

    }

    public void next(){
        this.next(null);
    }

    public void next(Object params){
        Optional.ofNullable(this.node.getConnections())
                .ifPresent(connections -> connections.stream()
                        .filter(conn -> this.conditionExecute(conn.getCondition(), params))
                        .map(NodeConnection::getNode)
                        .forEach(node -> this.copy(node).start(params)));
    }

}
