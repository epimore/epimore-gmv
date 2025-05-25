package cn.epimore.gmv.ser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String dateStr = p.getText();
        // 去掉时区部分（如 "Z" 或 "+08:00"），只解析日期时间
        return LocalDateTime.parse(dateStr.replaceAll("Z$", ""), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
