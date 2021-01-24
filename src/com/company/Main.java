package com.company;

import com.company.command.CommandStack;
import com.company.shop.Product;
import com.company.shop.ShoppingCart;
import com.company.shop.ShoppingCartItem;

public class Main {

    public static void main(String[] args) {

        ShoppingCart shoppingCart = new ShoppingCart();
        CommandStack commandStack = new CommandStack();


      //  Product product1 = new Product("Milk");
        Product product2 = new Product("Bananas");
        Product product3 = new Product("Apples");
        Product product4 = new Product("Pears");

       //ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product1, 100.00, 1);
        ShoppingCartItem shoppingCartItem2 = new ShoppingCartItem(product2, 10, 2);
        ShoppingCartItem shoppingCartItem3 = new ShoppingCartItem(product3, 5, 1);
        ShoppingCartItem shoppingCartItem4 = new ShoppingCartItem(product4, 10, 1);

       // shoppingCart.addCartItem(shoppingCartItem);
        shoppingCart.addCartItem(shoppingCartItem2);
        shoppingCart.addCartItem(shoppingCartItem3);
        shoppingCart.addCartItem(shoppingCartItem4);

        System.out.println(shoppingCart.receipt());


       shoppingCart.undo(commandStack);
       shoppingCart.redo(commandStack);

        shoppingCartItem3.setQuantity(2);
      //  shoppingCartItem4.setQuantity(6);


        shoppingCart.undo(commandStack);
        shoppingCart.redo(commandStack);



    }

}
