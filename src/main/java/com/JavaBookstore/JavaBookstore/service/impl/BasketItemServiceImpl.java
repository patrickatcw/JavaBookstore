package com.JavaBookstore.JavaBookstore.service.impl;

import com.JavaBookstore.JavaBookstore.domain.BasketItem;
import com.JavaBookstore.JavaBookstore.domain.ShoppingBasket;
import com.JavaBookstore.JavaBookstore.repository.BasketItemRepository;
import com.JavaBookstore.JavaBookstore.service.BasketItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BasketItemServiceImpl implements BasketItemService {

    @Autowired
    private BasketItemRepository basketItemRepository;  //need to make repository interface

    public List<BasketItem> findByShoppingBasket (ShoppingBasket shoppingBasket){

        return basketItemRepository.findByShoppingBasket(shoppingBasket);

    }

    public BasketItem updateBasketItem(BasketItem basketItem) {
        BigDecimal bigDecimal = new BigDecimal(basketItem.getBook().getOurPrice()).multiply(new BigDecimal(basketItem.getQty()));

        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        basketItem.setSubtotal(bigDecimal);

        basketItemRepository.save(basketItem);

        return basketItem;
    }

}
