package tech.guyi.component.flows.db.converter;

import org.springframework.stereotype.Component;
import tech.guyi.component.flows.api.flow.FlowConditionEngineType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

/**
 * @author guyi
 * @date 2021/3/19 23:48
 */
@Component
@Converter(autoApply = true)
public class FlowConditionEngineTypeConverter implements AttributeConverter<FlowConditionEngineType, String> {

    @Override
    public String convertToDatabaseColumn(FlowConditionEngineType engineType) {
        return Optional.ofNullable(engineType)
                .map(FlowConditionEngineType::getValue)
                .orElse(null);
    }

    @Override
    public FlowConditionEngineType convertToEntityAttribute(String s) {
        return Optional.ofNullable(s)
                .map(FlowConditionEngineType::getByValue)
                .orElse(null);
    }
}
