package tech.guyi.component.flows.service.entry;

import lombok.Data;
import tech.guyi.component.flows.service.enums.RunnerType;

import java.util.List;

/**
 * @author guyi
 * @date 2021/3/20 16:21
 */
@Data
public class RunnerEntry {

    private String name;
    private String runner;
    private RunnerType type;
    private List<RunnerProperty> properties;

}
