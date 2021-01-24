package com.company.shop;

import com.company.command.CommandStack;
import com.company.command.CommandState;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;


public class ShoppingCartItem {
    private final BigDecimal itemCost;
    private final Product product;
    private int quantity;

    public ShoppingCartItem(@NotNull Product product, double itemCost, int quantity) {
        this.itemCost = BigDecimal.valueOf(itemCost);
        this.product = product;
        this.quantity = quantity;
    }

    public int quantity(){
        return quantity;
    }

    public Product product() {
        return product;
    }

    public BigDecimal itemCost() {
        return itemCost;
    }


    //Command Pattern
    public void setQuantity(int newQuantity) {
        int initialQuantity = this.quantity;


        CommandStack.addState(new CommandState(() -> {
            System.out.println("Undoing set quantity");
            this.quantity = initialQuantity;
        }, () -> {
            System.out.println("Redoing set quantity");
            this.quantity = newQuantity;
        }));

        this.quantity = newQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingCartItem lineItem = (ShoppingCartItem) o;

        if (quantity != lineItem.quantity) return false;
        if (!itemCost.equals(lineItem.itemCost)) return false;
        return product.equals(lineItem.product);
    }

    @Override
    public int hashCode() {
        int result = itemCost.hashCode();
        result = 31 * result + product.hashCode();
        result = 31 * result + quantity;
        return result;
    }
}
