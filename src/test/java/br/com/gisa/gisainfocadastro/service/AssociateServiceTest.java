package br.com.gisa.gisainfocadastro.service;

import br.com.gisa.gisainfocadastro.data.AssociateEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class AssociateServiceTest {

    @Autowired
    AssociateService service;

    @Test
    void shouldInsertAssociate() {
        AssociateEntity entity = new AssociateEntity();
        entity.setFullName("Jane Smith");
        entity.setBirthDate(LocalDate.of(2000, 12, 31));
        entity.setEmail("johndoe@gmail.com");

        AssociateEntity result = service.create(entity);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getCreatedAt()).isNotNull();
        assertThat(result.getCreatedAt()).isNotNull();
    }

    @Test
    void shouldNotFoundAssociate() {
        Optional<AssociateEntity> result = service.findById(1L);
        assertThat(result).isEmpty();
    }

    @Test
    void shouldFoundAssociate() {
        AssociateEntity entity = new AssociateEntity();
        entity.setFullName("Jane Smith");
        entity.setBirthDate(LocalDate.of(2000, 12, 31));
        entity.setEmail("johndoe@gmail.com");
        AssociateEntity saved = service.create(entity);

        AssociateEntity result = service.findById(saved.getId()).orElseThrow(IllegalStateException::new);

        assertThat(result)
            .usingRecursiveComparison()
            .ignoringFields("id", "createdAt", "updatedAt")
            .isEqualTo(entity);
    }

}