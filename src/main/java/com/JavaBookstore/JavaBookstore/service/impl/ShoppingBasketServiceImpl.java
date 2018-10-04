package com.JavaBookstore.JavaBookstore.service.impl;

import com.JavaBookstore.JavaBookstore.domain.BasketItem;
import com.JavaBookstore.JavaBookstore.domain.ShoppingBasket;
import com.JavaBookstore.JavaBookstore.repository.ShoppingBasketRepository;
import com.JavaBookstore.JavaBookstore.service.BasketItemService;
import com.JavaBookstore.JavaBookstore.service.ShoppingBasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ShoppingBasketServiceImpl implements ShoppingBasketService {

    @Autowired
    private BasketItemService basketItemService;

    @Autowired
    private ShoppingBasketRepository shoppingBasketRepository;

    // public void updateShoppingBasket(ShoppingBasket shoppingBasket){
        public ShoppingBasket updateShoppingBasket(ShoppingBasket shoppingBasket){ //change in shoppingbasketservice interface

        BigDecimal basketTotal = new BigDecimal(0);

        List<BasketItem> basketItemList = basketItemService.findByShoppingBasket(shoppingBasket); //autowire basketitemservice

        for (BasketItem basketItem : basketItemList){

            if(basketItem.getBook(). getInStockNumber() > 0){

                basketItemService.updateBasketItem(basketItem);  //need to make this method, done
                basketTotal = basketTotal.add(basketItem.getSubtotal());

            }

        }

        shoppingBasket.setGrandTotal(basketTotal);

        shoppingBasketRepository.save(shoppingBasket);   //need to make shoppingBasketRepository interface, then autowire in here

        return shoppingBasket;

    }

}
