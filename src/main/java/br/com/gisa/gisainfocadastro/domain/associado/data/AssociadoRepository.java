package br.com.gisa.gisainfocadastro.domain.associado.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociadoRepository extends JpaRepository<AssociadoEntity, Long> {

    Optional<AssociadoEntity> findByEmail(String email);
}
