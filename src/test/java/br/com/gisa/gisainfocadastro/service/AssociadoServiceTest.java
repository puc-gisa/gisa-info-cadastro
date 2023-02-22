package br.com.gisa.gisainfocadastro.service;

import br.com.gisa.gisainfocadastro.data.AssociadoEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class AssociadoServiceTest {

    @Autowired
    AssociadoService service;

    @Test
    void shouldInsertAssociado() {
        AssociadoEntity entity = new AssociadoEntity();
        entity.setNome("Jane Smith");
        entity.setDataNascimento(LocalDate.of(2000, 12, 31));
        entity.setEmail("johndoe@gmail.com");

        AssociadoEntity result = service.create(entity);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getDataCriacao()).isNotNull();
        assertThat(result.getDataCriacao()).isNotNull();
    }

    @Test
    void shouldNotFoundAssociado() {
        Optional<AssociadoEntity> result = service.findById(1L);
        assertThat(result).isEmpty();
    }

    @Test
    void shouldFoundAssociado() {
        AssociadoEntity entity = new AssociadoEntity();
        entity.setNome("Jane Smith");
        entity.setDataNascimento(LocalDate.of(2000, 12, 31));
        entity.setEmail("johndoe@gmail.com");
        AssociadoEntity saved = service.create(entity);

        AssociadoEntity result = service.findById(saved.getId()).orElseThrow(IllegalStateException::new);

        assertThat(result)
            .usingRecursiveComparison()
            .ignoringFields("id", "createdAt", "updatedAt")
            .isEqualTo(entity);
    }

}