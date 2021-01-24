package com.company.shop;


import com.company.command.CommandStack;
import com.company.command.CommandState;
import com.company.strategy.Discount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ShoppingCart {

     final ArrayList<ShoppingCartItem> items = new ArrayList<>();
     final ArrayList<Discount> discounts = new ArrayList<>();
     BigDecimal bestOffer = BigDecimal.ZERO;
     BigDecimal finalOffer = BigDecimal.ZERO;

    public void addCartItem(ShoppingCartItem item) {
        items.add(item);
        addToStack(item);
    }

    public void addToStack(ShoppingCartItem item) {
        CommandStack.addState(new CommandState(() -> {items.remove(item);}, () -> {items.add(item);}));
    }


    public Stream<ShoppingCartItem> stream(){
        return items.stream();
    }

    public void useDiscount(Discount discount) {
        discounts.add(discount);
    }


    public BigDecimal calculatePrice(){
        var sum = BigDecimal.ZERO;
        for (var item: items) {
            sum = item.itemCost().multiply(BigDecimal.valueOf(item.quantity())).add(sum);
        }

        for (var disc : discounts){
            var totalDiscount = disc.applyDiscount(items);

            if (sum.subtract(totalDiscount).intValue() > bestOffer.intValue()) {
                bestOffer = sum.subtract(totalDiscount);
            }

            finalOffer = sum.subtract(bestOffer);

        }
        return sum;
    }

    public void undo(CommandStack stack){

        stack.undoState();
    }

    public void redo(CommandStack stack){

        stack.redoState();
    }

    public String receipt() {
        String line = "--------------------------------\n";
        StringBuilder sb = new StringBuilder();
        sb.append(line);
        var list = items.stream()
                .sorted(Comparator.comparing(item -> item.product().name()))
                .collect(Collectors.toList());
        for (var each : list) {
            sb.append(String.format("%-24s % 7.2f\n", each.product().name(), each.itemCost()));
        }
        sb.append(line);
        sb.append(String.format("%24s% 8.2f", "DISCOUNT:", finalOffer));
        sb.append(String.format("%24s% 8.2f", "TOTAL:", calculatePrice()));
        return sb.toString();
    }
}