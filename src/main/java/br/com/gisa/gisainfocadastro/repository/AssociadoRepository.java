package br.com.gisa.gisainfocadastro.repository;

import br.com.gisa.gisainfocadastro.domain.AssociadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociadoRepository extends JpaRepository<AssociadoEntity, Long> {

    Optional<AssociadoEntity> findByEmail(String email);

    Optional<AssociadoEntity> findByCpf(String cpf);

}
