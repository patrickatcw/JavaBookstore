package com.JavaBookstore.JavaBookstore.domain;

import javax.persistence.*;

@Entity
public class BookToBasketItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name="basket_item_id")
    private BasketItem basketItem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BasketItem getBasketItem() {
        return basketItem;
    }

    public void setBasketItem(BasketItem basketItem) {
        this.basketItem = basketItem;
    }


}


