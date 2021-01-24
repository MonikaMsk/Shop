package com.company.strategy;

import com.company.shop.ShoppingCartItem;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface Discount {

    BigDecimal applyDiscount(ArrayList<ShoppingCartItem> items);

}
