package tech.guyi.component.flows;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import tech.guyi.component.flows.api.runner.NodeRunner;
import tech.guyi.component.flows.service.entry.RunnerEntry;

import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author guyi
 * @date 2021/3/19 21:04
 */
@Component
public class NodeRunnerFactory {

    private final Gson gson = new Gson();
    private final URLClassLoader loader = new URLClassLoader(new URL[]{}, Thread.currentThread().getContextClassLoader());

    @SneakyThrows
    public List<RunnerEntry> runners(){
        Enumeration<URL> urls = loader.getResources("runner-metadata.json");
        Type type = new TypeToken<List<RunnerEntry>>(){}.getType();
        List<RunnerEntry> entries = new LinkedList<>();
        while (urls.hasMoreElements()){
            entries.addAll(
                    this.gson.fromJson(
                            IOUtils.toString(urls.nextElement().openConnection().getInputStream(), StandardCharsets.UTF_8), type));
        }
        return entries;
    }

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
            Class<?> classes = loader.loadClass(className);
            if (NodeRunner.class.isAssignableFrom(classes)){
             return Optional.of((NodeRunner) classes.newInstance());
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
