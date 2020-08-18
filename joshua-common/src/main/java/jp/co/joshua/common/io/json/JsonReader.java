package jp.co.joshua.common.io.json;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON形式のReaderクラス
 *
 * @version 1.0.0
 */
public class JsonReader {

    public <T> T read(String path, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        return read(new File(path), clazz);
    }

    public <T> T read(File json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        return new ObjectMapper().readValue(json, clazz);
    }

    public String read(Object bean) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(bean);
    }

    public <T> T read(InputStream is, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        return new ObjectMapper().readValue(is, clazz);
    }

}
