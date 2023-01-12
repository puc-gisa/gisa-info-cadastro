package br.com.gisa.gisainfocadastro.controller;

import br.com.gisa.gisainfocadastro.data.AssociateEntity;
import br.com.gisa.gisainfocadastro.domain.AssociateDto;
import br.com.gisa.gisainfocadastro.service.AssociateService;
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
@RequestMapping("/associate")
@RequiredArgsConstructor
public class AssociateController {

    private final AssociateService service;

    private final ModelMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<AssociateDto> getById(@PathVariable Long id) {
        Optional<AssociateEntity> associate = service.findById(id);
        return associate.map(associateEntity ->
            ok(mapper.map(associateEntity, AssociateDto.class))).orElseGet(
            () -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<AssociateDto> listAll() {
        List<AssociateEntity> associates = service.findAll();
        return associates.stream()
            .map(a -> mapper.map(a, AssociateDto.class))
            .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody AssociateDto associateDto) {
        AssociateEntity entity = mapper.map(associateDto, AssociateEntity.class);
        AssociateEntity saved = service.create(entity);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody AssociateDto associateDto) {
        AssociateEntity entity = mapper.map(associateDto, AssociateEntity.class);
        service.update(id, entity);

        return ResponseEntity.ok().build();
    }

}
