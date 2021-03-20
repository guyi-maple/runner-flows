package tech.guyi.component.flows.db.converter;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import tech.guyi.component.flows.db.entry.NodeProperties;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

/**
 * @author guyi
 * @date 2021/3/19 21:30
 */
@Component
@Converter(autoApply = true)
public class NodePropertiesConverter implements AttributeConverter<NodeProperties, String> {

    private final Gson gson = new Gson();

    @Override
    public String convertToDatabaseColumn(NodeProperties properties) {
        return Optional.ofNullable(properties)
                .map(this.gson::toJson)
                .orElse(null);
    }

    @Override
    public NodeProperties convertToEntityAttribute(String s) {
        return Optional.ofNullable(s)
                .map(json -> this.gson.fromJson(json, NodeProperties.class))
                .orElse(null);
    }
}
