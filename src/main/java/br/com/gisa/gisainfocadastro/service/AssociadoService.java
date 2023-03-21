package br.com.gisa.gisainfocadastro.service;

import br.com.gisa.gisainfocadastro.domain.AssociadoEntity;
import br.com.gisa.gisainfocadastro.domain.EnderecoEntity;
import br.com.gisa.gisainfocadastro.exceptions.NotFoundException;
import br.com.gisa.gisainfocadastro.exceptions.ValidationException;
import br.com.gisa.gisainfocadastro.repository.AssociadoRepository;
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
        validateNewAssociado(associadoEntity);
        associadoEntity.getEndereco().setAssociado(associadoEntity);
        return repository.save(associadoEntity);
    }

    public AssociadoEntity update(Long id, AssociadoEntity request) {
        AssociadoEntity associadoToSave = findById(id).orElseThrow(NotFoundException::new);
        log.info("Atualizando associado com id={}", id);

        associadoToSave.setNome(request.getNome());
        associadoToSave.setDataNascimento(request.getDataNascimento());
        EnderecoEntity enderecoToSave = associadoToSave.getEndereco();
        enderecoToSave.setLogradouro(request.getEndereco().getLogradouro());
        enderecoToSave.setNumero(request.getEndereco().getNumero());
        enderecoToSave.setComplemento(request.getEndereco().getComplemento());
        enderecoToSave.setBairro(request.getEndereco().getBairro());
        enderecoToSave.setCidade(request.getEndereco().getCidade());
        enderecoToSave.setEstado(request.getEndereco().getEstado());

        return repository.save(associadoToSave);
    }

    private void validateNewAssociado(AssociadoEntity associadoEntity) {
        this.validateUniqueCpf(associadoEntity.getCpf());
        this.validateUniqueEmail(associadoEntity.getEmail());
    }

    private void validateUniqueEmail(String email) {
        Optional<AssociadoEntity> associado = repository.findByEmail(email);
        associado.ifPresent(entity -> {
            throw new ValidationException("Já existe um associado com o email: " + email);
        });
    }

    private void validateUniqueCpf(String cpf) {
        Optional<AssociadoEntity> associado = repository.findByCpf(cpf);
        associado.ifPresent(entity -> {
            throw new ValidationException("Já existe um associado com o cpf: " + cpf);
        });
    }

}
