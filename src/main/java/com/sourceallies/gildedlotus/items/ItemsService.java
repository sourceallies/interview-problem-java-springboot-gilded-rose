package com.sourceallies.gildedlotus.items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class ItemsService {
    private final ItemsRepositoryService repositoryService;

    public ItemsService(ItemsRepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    public Collection<Item> getAllItems() {
        return repositoryService.getAllItems();
    }

    public UUID create(CreateItem item) {
        var itemDto = Item.builder()
            .name(item.name())
            .description(item.description())
            .sellIn(item.sellIn())
            .quality(item.quality())
            .build();
        return repositoryService.createItem(itemDto);
    }

    public Item getById(UUID id) {
        return repositoryService.getItemById(id);
    }

    public void deleteById(UUID id) {
        repositoryService.deleteItemById(id);
    }

    public Collection<Item> refreshItems() {
        var allItems = repositoryService.getAllItems();
        Iterator<Item> items = allItems.iterator();
        ArrayList<Item> items2 = new ArrayList<Item>();
        var i  =0;
        while (i < allItems.size()) {
            i++;
            Item item1 = items.next();

            item1.setQuality(item1.getQuality() - 1);

            if (!item1.getName().contains("Conjured")) {
                if (item1.getName().equals("Aged Brie")) {
                    item1.setQuality(item1.getQuality() < 50 ? item1.getQuality() + 2 : 50);
                    if (item1.getQuality() > 50) {
                        item1.setQuality(50);
                    }
                }

                if (item1.getName().equals("Sulfuras")) {
                    item1.setQuality(item1.getQuality() < 50 ? item1.getQuality() + 1 : 50);
                    continue;
                }

                item1.setSellIn(item1.getSellIn() - 1);

                if (item1.getName().equals("Backstage passes")) {
                    item1.setQuality(item1.getQuality()+2);
                    if (item1.getSellIn() < 10) item1.setQuality(item1.getQuality() < 50 ? 
                        item1.getQuality() + 1 : 
                        50);
                    if (item1.getSellIn() < 5) item1.setQuality(item1.getQuality() < 50 ? 
                        item1.getQuality() + 1 :
                        50);
                    if (item1.getSellIn() < 1) item1.setQuality(item1.getQuality() <= 50 ? 
                        0 : 
                        50);
                }
            } else {
                if (item1.getName().equals("Aged Brie")) {
                    item1.setQuality(item1.getQuality() < 50 ? item1.getQuality() + 2 : 50);
                    continue;
                }

                if (item1.getName().equals("Sulfuras")) {
                    item1.setQuality(item1.getQuality() < 50 ? item1.getQuality() + 1 : 50);
                    continue;
                }
                item1.setQuality(item1.getQuality() -1);
                item1.setSellIn(item1.getSellIn() - 1);
            }

            if (item1.getSellIn() < 0)
                item1.setSellIn(0);
            
                if (item1.getQuality() < 0){
                    item1.setDescription(item1.getDescription());;
                    item1.setQuality(0);
                }

                repositoryService.deleteItemById(item1.getId());
                repositoryService.createItem(item1);
                items2.add(item1);
            }

            return List.copyOf(items2);
        }
    }
