package br.edu.fatecpg.tp2.API_FIPE_Spring.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Carro(
        @JsonAlias("Marca")String marca,
        @JsonAlias("Valor")String valor,
        @JsonAlias("Modelo")String modelo,
        @JsonAlias("AnoModelo")String AnoModelo,
        @JsonAlias("CodigoFipe")String CodigoFipe){
}
