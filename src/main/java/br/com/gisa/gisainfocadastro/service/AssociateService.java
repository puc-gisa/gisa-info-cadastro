package br.com.gisa.gisainfocadastro.service;

import br.com.gisa.gisainfocadastro.data.AssociateEntity;
import br.com.gisa.gisainfocadastro.data.AssociateRepository;
import br.com.gisa.gisainfocadastro.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssociateService {

    private final AssociateRepository repository;

    public Optional<AssociateEntity> findById(Long associateId) {
        return repository.findById(associateId);
    }

    public List<AssociateEntity> findAll() {
        return repository.findAll();
    }

    public AssociateEntity create(AssociateEntity associateEntity) {
        return repository.save(associateEntity);
    }

    public AssociateEntity update(Long id, AssociateEntity associateRequest) {
        AssociateEntity entity = findById(id).orElseThrow(NotFoundException::new);
        log.info("Updating associate with id={}", id);

        entity.setBirthDate(associateRequest.getBirthDate());
        entity.setEmail(associateRequest.getEmail());
        entity.setFullName(associateRequest.getFullName());
        return repository.save(associateRequest);
    }

}
