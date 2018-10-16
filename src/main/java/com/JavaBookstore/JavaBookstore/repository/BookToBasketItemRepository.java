package com.JavaBookstore.JavaBookstore.repository;

import com.JavaBookstore.JavaBookstore.domain.BookToBasketItem;
import org.springframework.data.repository.CrudRepository;

public interface BookToBasketItemRepository extends CrudRepository <BookToBasketItem, Long> {

}


//now wire it up in basketitemserviceimpl