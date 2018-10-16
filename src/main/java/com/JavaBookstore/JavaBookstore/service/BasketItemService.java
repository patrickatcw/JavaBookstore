package com.JavaBookstore.JavaBookstore.service;

import com.JavaBookstore.JavaBookstore.domain.BasketItem;
import com.JavaBookstore.JavaBookstore.domain.ShoppingBasket;
import com.JavaBookstore.JavaBookstore.domain.User;


import java.awt.print.Book;
import java.util.List;

public interface BasketItemService {

    List<BasketItem> findByShoppingBasket(ShoppingBasket shoppingBasket);

    /*void updateBasketItem(BasketItem basketItem);*/ //then go to basketitemserviceimpl
    BasketItem updateBasketItem(BasketItem basketItem);

    BasketItem addBookToBasketItem(Book book, User user, int qty);

}
