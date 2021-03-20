package tech.guyi.component.flows.db.converter;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import tech.guyi.component.flows.db.entry.NodePosition;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

/**
 * @author guyi
 * @date 2021/3/21 00:38
 */
@Component
@Converter(autoApply = true)
public class NodePositionConverter implements AttributeConverter<NodePosition, String> {

    private final Gson gson = new Gson();

    @Override
    public String convertToDatabaseColumn(NodePosition nodePosition) {
        return Optional.ofNullable(nodePosition)
                .map(gson::toJson)
                .orElse(null);
    }

    @Override
    public NodePosition convertToEntityAttribute(String s) {
        return Optional.ofNullable(s)
                .map(json -> gson.fromJson(json, NodePosition.class))
                .orElse(null);
    }

}
