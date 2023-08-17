package com.sourceallies.gildedlotus.entrypoints.controllers;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sourceallies.gildedlotus.entities.ItemsResponse;
import com.sourceallies.gildedlotus.items.CreateItem;
import com.sourceallies.gildedlotus.items.Item;
import com.sourceallies.gildedlotus.items.ItemsService;

@RestController
public class ItemsController {
    private final ItemsService itemsService;

    public ItemsController(ItemsService service) {
        itemsService = service;
    }
    
    @GetMapping(value = "/items", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ItemsResponse getAllItems() {
        var items = itemsService.getAllItems();
        return new ItemsResponse(items.size(), items);
    }

    @PostMapping(value = "/items", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createItem(@RequestBody(required = true) CreateItem item) {
        var newId = itemsService.create(item);
        URI uri = UriComponentsBuilder.fromPath("/items/%s".formatted(newId)).build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/items/{itemId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Item getItemById(@PathVariable(required = true) UUID itemId) {
        return itemsService.getById(itemId);
    }

    @DeleteMapping(value = "/items/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItemById(@PathVariable(required = true) UUID itemId) {
        itemsService.deleteById(itemId);
    }

    @PostMapping(value = "/refresh", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ItemsResponse refreshAndReturnItems() {
        var items = itemsService.refreshItems();
        return new ItemsResponse(items.size(), items);
    }
}
