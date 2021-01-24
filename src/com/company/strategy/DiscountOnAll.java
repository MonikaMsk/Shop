package com.company.strategy;

import com.company.shop.ShoppingCartItem;

import java.math.BigDecimal;
import java.util.ArrayList;

public class DiscountOnAll implements Discount {


    @Override
    public BigDecimal applyDiscount(ArrayList<ShoppingCartItem> items) {
        var sum = BigDecimal.ZERO;
        var finalAmount = BigDecimal.ZERO;

        for (var item: items) {
            sum = item.itemCost().multiply(BigDecimal.valueOf(item.quantity())).add(sum);
        }

        if(sum.doubleValue() >= 500){
            finalAmount = sum.multiply(BigDecimal.valueOf(0.1));


        }
        return finalAmount;
    }
}
