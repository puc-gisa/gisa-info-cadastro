package br.com.gisa.gisainfocadastro.repository;

import br.com.gisa.gisainfocadastro.domain.PlanoSaudeIndividualEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanoSaudeRepository extends JpaRepository<PlanoSaudeIndividualEntity, Long> {

    List<PlanoSaudeIndividualEntity> findByIdAssociado(Long idAssociado);

}
