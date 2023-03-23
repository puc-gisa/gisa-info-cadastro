package br.com.gisa.gisainfocadastro.repository;

import br.com.gisa.gisainfocadastro.domain.AutorizacaoExameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorizacaoExameRepository extends JpaRepository<AutorizacaoExameEntity, Long> {

    List<AutorizacaoExameEntity> findByIdAssociadoOrderByDataSolicitacaoDesc(Long idAssociado);

}
