package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item... items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            updateSingleItem(items[i]);
        }
    }

    private void updateSingleItem(Item item) {
        DefaultStrategy strategy = getStrategy(item);

        updateQuality(item);

        updateSellIn(item);

        if (item.sellIn < 0) {
            updateQualityAfterSellIn(item);
        }
    }

    private DefaultStrategy getStrategy(Item item) {
        return new DefaultStrategy();
    }

    private void updateQualityAfterSellIn(Item item) {
        if (item.name.equals("Aged Brie")) {
            increaseQuality(item);
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            item.quality = 0;
        } else {
            decreaseQuality(item);
        }
    }

    private void updateSellIn(Item item) {
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn = item.sellIn - 1;
        }
    }

    private void updateQuality(Item item) {
        if (item.name.equals("Aged Brie")) {
            increaseQuality(item);
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            increaseQuality(item);

            if (item.sellIn <= 10) {
                increaseQuality(item);
            }

            if (item.sellIn <= 5) {
                increaseQuality(item);
            }

        } else {
            decreaseQuality(item);
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > 0 && !item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.quality = item.quality - 1;
        }
    }

    private void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    private class DefaultStrategy {
    }
}
