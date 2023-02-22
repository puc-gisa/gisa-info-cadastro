package br.com.gisa.gisainfocadastro.domain.endereco.service;

import br.com.gisa.gisainfocadastro.domain.associado.service.AssociadoService;
import br.com.gisa.gisainfocadastro.domain.endereco.data.EnderecoEntity;
import br.com.gisa.gisainfocadastro.domain.endereco.data.EnderecoRepository;
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

    private final AssociadoService associadoService;

    public Optional<EnderecoEntity> findByIdAssociado(Long idAssociado) {
        return repository.findByIdAssociado(idAssociado);
    }

    public List<EnderecoEntity> findAll() {
        return repository.findAll();
    }

    public EnderecoEntity create(Long idAssociado, EnderecoEntity entity) {
        if (!associadoService.exists(idAssociado)) {
            throw new NotFoundException();
        }

        entity.setIdAssociado(idAssociado);
        return repository.save(entity);
    }

    public EnderecoEntity update(Long idAssociado, EnderecoEntity updateEntity) {
        if (!associadoService.exists(idAssociado)) {
            throw new NotFoundException();
        }

        EnderecoEntity entity = findByIdAssociado(idAssociado).orElseThrow(NotFoundException::new);
        log.info("Atualizando endereço com idAssociado={}", idAssociado);

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
