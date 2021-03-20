package tech.guyi.component.flows.service.enums;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author guyi
 * @date 2021/3/20 16:29
 */
@Getter
@AllArgsConstructor
public enum RunnerType {

    @SerializedName("Input")
    INPUT("Input", "入口节点"),

    @SerializedName("Mid")
    MID("Mid", "中间节点"),

    @SerializedName("Output")
    OUTPUT("Output", "出口节点");

    private final String value;
    private final String text;

    public static RunnerType getByValue(String value){
        return Arrays.stream(values())
                .filter(type -> type.value.equals(value))
                .findFirst()
                .orElse(null);
    }

}
