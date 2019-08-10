package com.mainacad.service;

import com.mainacad.model.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ItemServiceTest {

    private static Item getRandomItem() {
        return new Item(
                "itemCode" + (int) (Math.random() * 1000),
                "ItemName" + (int) (Math.random() * 1000),
                (int) (Math.random() * 1000)
        );
    }

    @Test
    void addToDBAndFindByIDAndRemoveFromDB() {
        Item randomItem = getRandomItem();
        assertNull(randomItem.getId());
        randomItem = ItemService.addToDB(randomItem);
        assertNotNull(randomItem.getId());
        Item itemInDB = ItemService.findById(randomItem.getId());
        assertNotNull(itemInDB);
        ItemService.removeFromDB(itemInDB.getId());
        itemInDB = ItemService.findById(itemInDB.getId());
        assertNull(itemInDB);
    }
}