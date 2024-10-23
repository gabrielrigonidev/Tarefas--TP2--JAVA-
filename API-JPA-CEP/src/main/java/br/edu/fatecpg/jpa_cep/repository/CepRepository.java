package br.edu.fatecpg.jpa_cep.repository;

import br.edu.fatecpg.jpa_cep.model.Cep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CepRepository extends JpaRepository<Cep, Long> {
}
