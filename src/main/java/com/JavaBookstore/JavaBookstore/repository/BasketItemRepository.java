package com.JavaBookstore.JavaBookstore.repository;

import com.JavaBookstore.JavaBookstore.domain.BasketItem;
import com.JavaBookstore.JavaBookstore.domain.ShoppingBasket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface BasketItemRepository extends CrudRepository<BasketItem, Long> {

    List<BasketItem> findByShoppingBasket(ShoppingBasket shoppingBasket);
    //returning a list of shopping basket items by springboot

    //void deleteByBasketItem(BasketItem basketItem);

    //BasketItem findById();

}
