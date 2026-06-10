package com.rt_fo.api.factory.controller;

import com.rt_fo.api.factory.dto.FactoryDto;
import com.rt_fo.api.factory.dto.FactoryEditionRequest;
import com.rt_fo.api.factory.dto.FactoryWithReferencedDto;
import com.rt_fo.api.factory.entity.Factory;
import com.rt_fo.api.factory.service.FactoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/factories")
public class FactoryController {

    private final FactoryService factoryService;

    public FactoryController(FactoryService factoryService) {
        this.factoryService = factoryService;
    }

    @GetMapping
    public ResponseEntity<List<FactoryWithReferencedDto>> getFactories() {
        return ResponseEntity.ok(factoryService.getFactories());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FactoryDto> createFactory(@RequestBody FactoryEditionRequest request) {
        Factory factory = factoryService.createFactory(request.name());

        return ResponseEntity.ok(FactoryDto.fromEntity(factory));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FactoryDto> updateFactory(@PathVariable Integer id, @RequestBody FactoryEditionRequest request) {
        Factory factory = factoryService.updateFactory(id, request.name());

        return ResponseEntity.ok(FactoryDto.fromEntity(factory));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteFactory(@PathVariable Integer id) {
        factoryService.deleteFactory(id);

        return ResponseEntity.noContent()
                .build();
    }
}
