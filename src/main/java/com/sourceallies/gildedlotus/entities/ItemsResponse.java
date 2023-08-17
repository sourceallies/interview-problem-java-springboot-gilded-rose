package com.sourceallies.gildedlotus.entities;

import java.util.Collection;

import com.sourceallies.gildedlotus.items.Item;

public record ItemsResponse(int total, Collection<Item> values) {}
