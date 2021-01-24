package com.company.strategy;

import com.company.shop.ShoppingCartItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class DiscountCheapestItem implements Discount {
    @Override
    public BigDecimal applyDiscount(ArrayList<ShoppingCartItem> items) {
        var sum = BigDecimal.ZERO;
        var finalAmount = BigDecimal.ZERO;
        int quantity = 0;

        var sorted = items.stream()
                .sorted(Comparator.comparing(item -> item.itemCost()))
                .collect(Collectors.toList());

        var cheapest = sorted.get(0).itemCost();


           for (var item: items) {
            sum = item.itemCost().multiply(BigDecimal.valueOf(item.quantity())).add(sum);
                System.out.println(sum + " SUM");

           if(item.quantity() > 1 || items.size() > 1) {
               finalAmount = sum.subtract(cheapest);
               System.out.println(finalAmount + " Final amount");

           }
        }

        return finalAmount;
    }
}
