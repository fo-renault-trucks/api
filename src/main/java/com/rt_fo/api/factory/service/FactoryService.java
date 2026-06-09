package com.rt_fo.api.factory.service;

import com.rt_fo.api.factory.dto.FactoryWithReferencedDto;
import com.rt_fo.api.factory.entity.Factory;
import com.rt_fo.api.factory.exception.FactoryNotFoundException;
import com.rt_fo.api.factory.repository.FactoryRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class FactoryService {

    private final FactoryRepository factoryRepository;

    public FactoryService(FactoryRepository factoryRepository) {
        this.factoryRepository = factoryRepository;
    }

    public List<FactoryWithReferencedDto> getFactories() {
        return factoryRepository.findAllWithReferenced();
    }

    public Set<Factory> getFactoriesById(List<Integer> ids) {
        Map<Integer, Factory> factories = factoryRepository.findAllById(ids)
                .stream()
                .collect(Collectors.toMap(Factory::getId, Function.identity()));

        if (factories.size() != ids.size()) {
            List<Integer> unknownFactoryIds = ids
                    .stream()
                    .filter(id -> !factories.containsKey(id))
                    .toList();

            throw new FactoryNotFoundException(unknownFactoryIds);
        }

        return Set.copyOf(factories.values());
    }

    public Factory createFactory(String name) {
        Factory factory = new Factory();
        factory.setName(name);

        return factoryRepository.save(factory);
    }

    public Factory updateFactory(Integer id, String name) {
        Factory factory = factoryRepository.findById(id)
                .orElseThrow(() -> new FactoryNotFoundException(Collections.singletonList(id)));

        factory.setName(name);

        return factoryRepository.save(factory);
    }

    public void deleteFactory(Integer id) {
        factoryRepository.deleteById(id);
    }
}
