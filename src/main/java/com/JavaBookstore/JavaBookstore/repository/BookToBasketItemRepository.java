package com.JavaBookstore.JavaBookstore.repository;

import com.JavaBookstore.JavaBookstore.domain.BasketItem;
import com.JavaBookstore.JavaBookstore.domain.BookToBasketItem;
import org.springframework.data.repository.CrudRepository;

public interface BookToBasketItemRepository extends CrudRepository <BookToBasketItem, Long> {

    void deleteByBasketItem(BasketItem basketItem);
}


//now wire it up in basketitemserviceimpl