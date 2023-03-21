package br.com.gisa.gisainfocadastro.service;

import br.com.gisa.gisainfocadastro.domain.AssociadoEntity;
import br.com.gisa.gisainfocadastro.domain.EnderecoEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    void shouldInsertAssociado() throws JsonProcessingException {
        AssociadoEntity associado = new AssociadoEntity();
        associado.setNome("Jane Smith");
        associado.setDataNascimento(LocalDate.of(2000, 12, 31));
        associado.setEmail("johndoe@gmail.com");
        associado.setCpf("12345678909");
        associado.setEndereco(buildEndereco());

        AssociadoEntity result = service.create(associado);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getDataCriacao()).isNotNull();
        assertThat(result.getDataCriacao()).isNotNull();
        assertThat(result.getEndereco()).isNotNull();
        assertThat(result.getEndereco().getAssociado().getId()).isEqualTo(result.getId());

    }

    @Test
    void shouldNotFoundAssociado() {
        Optional<AssociadoEntity> result = service.findById(1L);
        assertThat(result).isEmpty();
    }

    @Test
    void shouldFoundAssociado() throws JsonProcessingException {
        AssociadoEntity entity = new AssociadoEntity();
        entity.setNome("Jane Smith");
        entity.setDataNascimento(LocalDate.of(2000, 12, 31));
        entity.setEmail("johndoe@gmail.com");
        entity.setCpf("12345678909");
        entity.setEndereco(buildEndereco());
        AssociadoEntity saved = service.create(entity);

        AssociadoEntity result = service.findById(saved.getId()).orElseThrow(IllegalStateException::new);

        assertThat(result)
            .usingRecursiveComparison()
            .ignoringFields("id", "createdAt", "updatedAt")
            .isEqualTo(entity);
    }

    private static EnderecoEntity buildEndereco() {
        EnderecoEntity endereco = new EnderecoEntity();
        endereco.setLogradouro("Av 9 de Julhor");
        endereco.setNumero(1650);
        endereco.setBairro("Parque do Colégio");
        endereco.setCidade("Jundiaí");
        endereco.setEstado("SP");
        endereco.setComplemento("APTO 99");
        return endereco;
    }

}