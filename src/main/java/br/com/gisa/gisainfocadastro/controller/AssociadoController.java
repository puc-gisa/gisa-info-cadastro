package br.com.gisa.gisainfocadastro.controller;

import br.com.gisa.gisainfocadastro.domain.AssociadoEntity;
import br.com.gisa.gisainfocadastro.dto.AssociadoRequest;
import br.com.gisa.gisainfocadastro.dto.AssociadoResponse;
import br.com.gisa.gisainfocadastro.service.AssociadoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/associados")
@RequiredArgsConstructor
@Validated
public class AssociadoController {

    private final AssociadoService service;

    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<AssociadoResponse> getById(@PathVariable Long id) {
        Optional<AssociadoEntity> associado = service.findById(id);
        return associado.map(entity ->
                ok(modelMapper.map(entity, AssociadoResponse.class))).orElseGet(
                () -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<AssociadoResponse> listAll() {
        List<AssociadoEntity> associados = service.findAll();
        return associados.stream()
                .map(a -> modelMapper.map(a, AssociadoResponse.class))
                .collect(Collectors.toList());
    }

    @Validated(AssociadoRequest.OnInsert.class)
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody AssociadoRequest associadoRequest) throws JsonProcessingException {
        AssociadoEntity entity = modelMapper.map(associadoRequest, AssociadoEntity.class);
        AssociadoEntity saved = service.create(entity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody AssociadoRequest associadoRequest) throws JsonProcessingException {
        AssociadoEntity entity = modelMapper.map(associadoRequest, AssociadoEntity.class);
        service.update(id, entity);

        return ResponseEntity.ok().build();
    }

}
