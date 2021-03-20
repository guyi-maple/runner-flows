package tech.guyi.component.flows.api.node;

import lombok.Data;

/**
 * 节点连接
 * @author guyi
 * @date 2021/3/19 20:45
 */
@Data
public class NodeConnection {

    /**
     * 节点
     */
    private Node node;

    /**
     * 条件
     */
    private String condition;

}
