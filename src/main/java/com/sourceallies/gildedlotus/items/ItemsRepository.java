package com.sourceallies.gildedlotus.items;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository<Item, UUID> {
}
