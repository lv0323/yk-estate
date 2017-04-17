package com.lyun.estate.biz.file.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by Jeffrey on 2017-04-17.
 */
public class FileExt {

    private static ObjectMapper objectMapper;
    private static Logger logger = LoggerFactory.getLogger(FileExt.class);

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private Long createById;

    private Long deleteById;

    public Long getCreateById() {
        return createById;
    }

    public FileExt setCreateById(Long createById) {
        this.createById = createById;
        return this;
    }

    public Long getDeleteById() {
        return deleteById;
    }

    public FileExt setDeleteById(Long deleteById) {
        this.deleteById = deleteById;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("createById", createById)
                .toString();
    }

    public String toJsonString() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            logger.warn("{}", this);
            return null;
        }
    }

    public FileExt from(String str) {
        try {
            return objectMapper.readValue(str, FileExt.class);
        } catch (IOException e) {
            logger.warn("{}", this);
            return null;
        }
    }

}
