package com.sourceallies.gildedlotus.items;

import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class ItemsRepositoryService {
    private final ItemsRepository repository;

    public ItemsRepositoryService(ItemsRepository repository) {
        this.repository = repository;
    }

    public Collection<Item> getAllItems() {
        return repository.findAll();
    }

    public UUID createItem(Item item) {
        return repository.save(item).getId();
    }

    public Item getItemById(UUID id) {
        return repository.getReferenceById(id);
    }

    public void deleteItemById(UUID id) {
        repository.deleteById(id);
    }
}
