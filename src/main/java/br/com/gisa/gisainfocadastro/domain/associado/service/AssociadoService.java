package br.com.gisa.gisainfocadastro.domain.associado.service;

import br.com.gisa.gisainfocadastro.domain.associado.data.AssociadoEntity;
import br.com.gisa.gisainfocadastro.domain.associado.data.AssociadoRepository;
import br.com.gisa.gisainfocadastro.exceptions.NotFoundException;
import br.com.gisa.gisainfocadastro.exceptions.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssociadoService {

    private final AssociadoRepository repository;

    public Optional<AssociadoEntity> findById(Long idAssociado) {
        return repository.findById(idAssociado);
    }

    public boolean exists(Long idAssociado) {
        return repository.existsById(idAssociado);
    }

    public List<AssociadoEntity> findAll() {
        return repository.findAll();
    }

    public AssociadoEntity create(AssociadoEntity associadoEntity) {

        Optional<AssociadoEntity> associado = repository.findByEmail(associadoEntity.getEmail());
        if (associado.isPresent()) {
            throw new ValidationException("Já existe um associado com o email: " + associadoEntity.getEmail());
        }

        return repository.save(associadoEntity);
    }

    public AssociadoEntity update(Long id, AssociadoEntity associadoEntity) {
        AssociadoEntity entity = findById(id).orElseThrow(NotFoundException::new);
        log.info("Atualizando associado com id={}", id);

        entity.setDataNascimento(associadoEntity.getDataNascimento());
        entity.setEmail(associadoEntity.getEmail());
        entity.setNome(associadoEntity.getNome());
        return repository.save(entity);
    }

}
