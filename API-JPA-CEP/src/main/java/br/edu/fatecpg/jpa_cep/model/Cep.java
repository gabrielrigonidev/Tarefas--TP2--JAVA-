package br.edu.fatecpg.jpa_cep.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name="CEPs")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String cep;

    @JsonAlias("logradouro")
    private String rua;

    @JsonAlias("localidade")
    private String cidade;

    public Cep() {
    }

    @Override
    public String toString() {
        return "Cep{" +
                "cep='" + cep + '\'' +
                ", rua='" + rua + '\'' +
                ", cidade='" + cidade + '\'' +
                '}';
    }

    public String getCep() {
        return cep;
    }

    public String getRua() {
        return rua;
    }

    public String getCidade() {
        return cidade;
    }
}
