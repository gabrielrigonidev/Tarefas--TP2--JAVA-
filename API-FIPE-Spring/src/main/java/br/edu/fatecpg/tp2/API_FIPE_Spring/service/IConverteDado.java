package br.edu.fatecpg.tp2.API_FIPE_Spring.service;
import com.fasterxml.jackson.core.JsonProcessingException;
public interface IConverteDado {
    <T> T obterDado(String json, Class<T> classe) throws JsonProcessingException;
}
