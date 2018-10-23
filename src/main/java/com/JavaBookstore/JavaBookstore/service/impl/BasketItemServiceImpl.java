package com.JavaBookstore.JavaBookstore.service.impl;

import com.JavaBookstore.JavaBookstore.domain.*;
import com.JavaBookstore.JavaBookstore.repository.BasketItemRepository;
import com.JavaBookstore.JavaBookstore.repository.BookToBasketItemRepository;
import com.JavaBookstore.JavaBookstore.service.BasketItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BasketItemServiceImpl implements BasketItemService {

    @Autowired
    private BasketItemRepository basketItemRepository;  //need to make repository interface

    //wire it up after making booktobasketitemrepository interface
    @Autowired
    private BookToBasketItemRepository bookToBasketItemRepository;

    public List<BasketItem> findByShoppingBasket(ShoppingBasket shoppingBasket) {

        return basketItemRepository.findByShoppingBasket(shoppingBasket);

    }

    public BasketItem updateBasketItem(BasketItem basketItem) {
        BigDecimal bigDecimal = new BigDecimal(basketItem.getBook().getOurPrice()).multiply(new BigDecimal(basketItem.getQty()));

        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        basketItem.setSubtotal(bigDecimal);

        basketItemRepository.save(basketItem);

        return basketItem;
    }

    public BasketItem addBookToBasketItem(Book book, User user, int qty) {

        List<BasketItem> basketItemList = findByShoppingBasket(user.getShoppingBasket());

        for (BasketItem basketItem : basketItemList) {

            if(book.getId() == basketItem.getBook().getId()) {

                basketItem.setQty(basketItem.getQty()+qty);
                basketItem.setSubtotal(new BigDecimal(book.getOurPrice()).multiply(new BigDecimal(qty)));

                basketItemRepository.save(basketItem);
                return basketItem;

            }
        }

        BasketItem basketItem = new BasketItem();
        basketItem.setShoppingBasket(user.getShoppingBasket());
        basketItem.setBook(book);

        basketItem.setQty(qty);
        basketItem.setSubtotal(new BigDecimal(book.getOurPrice()).multiply(new BigDecimal(qty)));
        basketItem = basketItemRepository.save(basketItem);

        BookToBasketItem bookToBasketItem = new BookToBasketItem();
        bookToBasketItem.setBook(book);
        bookToBasketItem.setBasketItem(basketItem);
        bookToBasketItemRepository.save(bookToBasketItem); //define booktobasketitemrepository

        return basketItem;
    }

    public BasketItem findById(Long id) {

        //return basketItemRepository.findById();

        BasketItem basketItem = basketItemRepository.findById(id).get();
        return basketItem;

    }

    /*public void removeBasketItem(BasketItem basketItem) {

        bookToBasketItemRepository.deleteByBasketItem(basketItem); //make method
        basketItemRepository.delete(basketItem);

    }*/

    public void removeById(Long id) {

        //bookToBasketItemRepository.deleteById(id); //make method
        basketItemRepository.deleteById(id);

    }

}
