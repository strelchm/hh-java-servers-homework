package ru.strelchm.jersey.resource;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.strelchm.jersey.resource.utils.LocalDateTimeDeserializer;
import ru.strelchm.jersey.resource.utils.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class GetCounterResponse {
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime date;

    private Integer value;

    public GetCounterResponse(LocalDateTime date, Integer value) {
        this.date = date;
        this.value = value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}