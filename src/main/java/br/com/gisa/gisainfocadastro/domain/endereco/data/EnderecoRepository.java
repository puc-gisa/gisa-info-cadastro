package br.com.gisa.gisainfocadastro.domain.endereco.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {

    Optional<EnderecoEntity> findByIdAssociado(Long idAssociado);

}
