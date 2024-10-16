package br.edu.fatecpg.products.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDado implements IConverteDado{
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> JsonNode obterDado(String json) throws JsonProcessingException {
        return mapper.readTree(json);
    }
}
