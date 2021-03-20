package tech.guyi.component.flows.api.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 流节点连接条件执行引擎类型
 * @author guyi
 * @date 2021/3/19 20:48
 */
@Getter
@AllArgsConstructor
public enum FlowConditionEngineType {

    JAVASCRIPT("js","JavaScript");

    @JsonValue
    private final String value;
    private final String text;

    @JsonCreator
    public static FlowConditionEngineType getByValue(String value){
        return Arrays.stream(values())
                .filter(type -> type.value.equals(value))
                .findFirst()
                .orElse(null);
    }

}
