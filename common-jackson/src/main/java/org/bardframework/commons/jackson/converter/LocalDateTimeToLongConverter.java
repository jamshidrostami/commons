package org.bardframework.commons.jackson.converter;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Created by Vahid Zafari(v.zafari@chmail.ir) on 7/12/2016.
 */
@Component
public class LocalDateTimeToLongConverter extends JsonSerializer<LocalDateTime> implements Converter<LocalDateTime, Long> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void serialize(LocalDateTime dateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        try {
            jsonGenerator.writeNumber(this.convert(dateTime));
        } catch (Exception e) {
            logger.error("error when toModel local date time '{}' to mills", dateTime, e);
            jsonGenerator.writeNull();
        }
    }

    @Override
    public Long convert(LocalDateTime value) {
        return null == value ? null : value.toInstant(ZoneOffset.UTC).toEpochMilli();
    }
}