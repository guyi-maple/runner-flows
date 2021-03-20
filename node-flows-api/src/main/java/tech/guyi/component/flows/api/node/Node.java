package tech.guyi.component.flows.api.node;

import lombok.Data;
import tech.guyi.component.flows.api.runner.NodeRunner;

import java.util.List;
import java.util.Map;

/**
 * 执行节点
 * @author guyi
 * @date 2021/3/16 20:14
 */
@Data
public class Node {

    /**
     * 节点执行器
     */
    private NodeRunner runner;

    /**
     * 节点属性
     */
    private Map<String,Object> properties;

    /**
     * 节点连接
     */
    private List<NodeConnection> connections;

}
