package br.edu.fatecpg.tp2.API_IMDb_Spring.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Serie(
        @JsonAlias("Type")String tipo,
        @JsonAlias("Title")String titulo,
        @JsonAlias("Runtime")String duracao,
        @JsonAlias("Country")String pais,
        @JsonAlias("Poster")String poster) {
}