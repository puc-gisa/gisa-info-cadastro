package br.com.gisa.gisainfocadastro.controller;

import br.com.gisa.gisainfocadastro.data.AssociadoEntity;
import br.com.gisa.gisainfocadastro.domain.AssociadoRequest;
import br.com.gisa.gisainfocadastro.domain.AssociadoResponse;
import br.com.gisa.gisainfocadastro.service.AssociadoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
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
public class AssociadoController {

    private final AssociadoService service;

    private final ModelMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<AssociadoResponse> getById(@PathVariable Long id) {
        Optional<AssociadoEntity> associado = service.findById(id);
        return associado.map(entity ->
            ok(mapper.map(entity, AssociadoResponse.class))).orElseGet(
            () -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<AssociadoResponse> listAll() {
        List<AssociadoEntity> associados = service.findAll();
        return associados.stream()
            .map(a -> mapper.map(a, AssociadoResponse.class))
            .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody AssociadoRequest associadoRequest) {
        AssociadoEntity entity = mapper.map(associadoRequest, AssociadoEntity.class);
        AssociadoEntity saved = service.create(entity);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody AssociadoRequest associadoRequest) {
        AssociadoEntity entity = mapper.map(associadoRequest, AssociadoEntity.class);
        service.update(id, entity);

        return ResponseEntity.ok().build();
    }

}
