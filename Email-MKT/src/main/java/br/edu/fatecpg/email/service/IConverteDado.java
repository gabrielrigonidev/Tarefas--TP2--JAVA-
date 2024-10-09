package br.edu.fatecpg.email.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public interface IConverteDado {
    <T> JsonNode obterDado(String json) throws JsonProcessingException;
}
