package br.com.gisa.gisainfocadastro.service;

import br.com.gisa.gisainfocadastro.data.EnderecoEntity;
import br.com.gisa.gisainfocadastro.data.EnderecoRepository;
import br.com.gisa.gisainfocadastro.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnderecoService {

    private final EnderecoRepository repository;

    public Optional<EnderecoEntity> findById(Long id) {
        return repository.findById(id);
    }

    public List<EnderecoEntity> findAll() {
        return repository.findAll();
    }

    public EnderecoEntity create(EnderecoEntity entity) {
        return repository.save(entity);
    }

    public EnderecoEntity update(Long id, EnderecoEntity updateEntity) {
        EnderecoEntity entity = findById(id).orElseThrow(NotFoundException::new);
        log.info("Atualizando endereço com id={}", id);

        entity.setLogradouro(updateEntity.getLogradouro());
        entity.setNumero(updateEntity.getNumero());
        entity.setComplemento(updateEntity.getComplemento());
        entity.setBairro(updateEntity.getBairro());
        entity.setCidade(updateEntity.getCidade());
        entity.setEstado(updateEntity.getEstado().toUpperCase());
        entity.setCep(updateEntity.getCep());

        return repository.save(entity);
    }

}
