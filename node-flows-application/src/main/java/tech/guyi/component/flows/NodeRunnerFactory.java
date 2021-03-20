package tech.guyi.component.flows;

import org.springframework.stereotype.Component;
import tech.guyi.component.flows.api.runner.NodeRunner;

import java.util.Optional;

/**
 * @author guyi
 * @date 2021/3/19 21:04
 */
@Component
public class NodeRunnerFactory {

    public <T extends NodeRunner> Optional<T> create(Class<T> classes){
        try {
            return Optional.of(classes.cast(classes.newInstance()));
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<NodeRunner> create(String className){
        try {
            Class<?> classes = Class.forName(className);
            if (NodeRunner.class.isAssignableFrom(classes)){
             return Optional.of((NodeRunner) classes.newInstance());
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
