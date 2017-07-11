package com.gildedrose;

import static org.junit.Assert.*;

import junitparams.Parameters;
import org.junit.Test;

public class GildedRoseTest {

    //@Test
    public void conjuredItemsDegradesTwiceAsFast(){
        GildedRose app = new GildedRose(new Item("Conjured Mana Cake", 5, 10));
        app.updateQuality();
        assertEquals(8,app.items[0].quality);
    }

    @Test
    public void daysAreCountingDown(){
        Item standardItem = new Item("Elixir of the Mongoose", 5, 10);
        GildedRose app = new GildedRose(standardItem);
        app.updateQuality();
        assertEquals(standardItem.sellIn,4);
    }

    @Test
    public void degradesNormally(){
        Item standardItem = new Item("Elixir of the Mongoose", 5, 10);
        GildedRose app = new GildedRose(standardItem);
        app.updateQuality();
        assertEquals(standardItem.quality,9);
    }

    @Test
    public void degradesTwiceAsFastOnPassedSellIn(){
        Item standardItem = new Item("Elixir of the Mongoose", -2, 10);
        GildedRose app = new GildedRose(standardItem);
        app.updateQuality();
        assertEquals(standardItem.quality,8);
    }

    @Test
    public void qualityGetsNeverHigherThan50WithStandardItemToHigh(){
        Item standardItem = new Item("Elixir of the Mongoose", 10, 120);
        GildedRose app = new GildedRose(standardItem);
        app.updateQuality();
        // Wenn die Qualität zu hoch ist sinkt sie komischerweise ganze normal
        assertEquals(standardItem.quality,119);
    }

    @Test
    public void qualityGetsNeverHigherThan50(){
        // wir testen das mit Brie, weil die Qualität des Brie steigt
        GildedRose app = new GildedRose(new Item("Aged Brie", 0, 50));
        app.updateQuality();
        assertEquals(app.items[0].quality,50);
    }

    @Test
    public void brieImprovesInQualityBeforeSellIn(){
        GildedRose app = new GildedRose(new Item("Aged Brie", 2, 10));
        app.updateQuality();
        assertEquals(11,app.items[0].quality);
    }

    @Test
    public void brieImprovesInQualityOnPassedSellInTwice(){
        GildedRose app = new GildedRose(new Item("Aged Brie", 0, 10));
        app.updateQuality();
        assertEquals(12,app.items[0].quality);
    }

    @Test
    public void legendaryItemQualityNeverAlters(){
        GildedRose app = new GildedRose(new Item("Sulfuras, Hand of Ragnaros", 10, 80));
        app.updateQuality();
        assertEquals(80,app.items[0].quality);
    }

    @Test
    public void legendaryItemQualityNeverAltersAfterSellIn(){
        GildedRose app = new GildedRose(new Item("Sulfuras, Hand of Ragnaros", -2, 80));
        app.updateQuality();
        assertEquals(80,app.items[0].quality);
    }

    @Test
    public void backstagePassMoreThan10DaysToConcertIncreasesByOne(){
        GildedRose app = new GildedRose(new Item("Backstage passes to a TAFKAL80ETC concert", 12, 20));
        app.updateQuality();
        assertEquals(21, app.items[0].quality);
    }

    @Test
    public void backstagePassMore5To10DaysToConcertIncreasesByTwo(){
        GildedRose app = new GildedRose(new Item("Backstage passes to a TAFKAL80ETC concert", 7, 20));
        app.updateQuality();
        assertEquals(22, app.items[0].quality);
    }

    @Test
    public void backstagePassOnly5DaysToConcertIncreasesByThree(){
        GildedRose app = new GildedRose(new Item("Backstage passes to a TAFKAL80ETC concert", 3, 20));
        app.updateQuality();
        assertEquals(23, app.items[0].quality);
    }

    @Test
    public void backstagePassNotOver50(){
        GildedRose app = new GildedRose(new Item("Backstage passes to a TAFKAL80ETC concert", 3, 50));
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    public void backstagePassDropsToZeroAfterConcert(){
        GildedRose app = new GildedRose(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20));
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }
}
