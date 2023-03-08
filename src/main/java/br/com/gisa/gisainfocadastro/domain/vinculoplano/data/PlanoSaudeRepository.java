package br.com.gisa.gisainfocadastro.domain.vinculoplano.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanoSaudeRepository extends JpaRepository<PlanoSaudeIndividualEntity, Long> {

    List<PlanoSaudeIndividualEntity> findByIdAssociado(Long idAssociado);

}
