package com.JavaBookstore.JavaBookstore.service;

import com.JavaBookstore.JavaBookstore.domain.BasketItem;
import com.JavaBookstore.JavaBookstore.domain.ShoppingBasket;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BasketItemService {

    List<BasketItem> findByShoppingBasket(ShoppingBasket shoppingBasket);

    /*void updateBasketItem(BasketItem basketItem);*/ //then go to basketitemserviceimpl
    BasketItem updateBasketItem(BasketItem basketItem);

}
