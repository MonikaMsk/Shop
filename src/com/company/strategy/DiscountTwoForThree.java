package com.company.strategy;

import com.company.shop.ShoppingCartItem;

import java.math.BigDecimal;
import java.util.ArrayList;

public class DiscountTwoForThree implements Discount {

    @Override
    public BigDecimal applyDiscount(ArrayList<ShoppingCartItem> items) {

        var GetItemCost = BigDecimal.ZERO;

        // Items in basket
        for (var item : items) {
            for (var j = 0; j < item.quantity(); j++ ) {
                System.out.println(j + " <- j original " + item.product().name());
            }

            // Quantity of item
            for (var i = 2; i < item.quantity(); i+=3 ) {
                System.out.println(i + " <- i " + item.product().name());
                GetItemCost = item.itemCost().add(GetItemCost);
            }
            System.out.println(GetItemCost + " <- Total Saved Amount");
        }
        return GetItemCost; // Returns product cost multiplied by amount of free items
    }
    }


