package com.JavaBookstore.JavaBookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class BasketItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int qty;
    private BigDecimal subtotal;

    @OneToOne
    private Book book;

    @OneToMany(mappedBy = "basketItem")
    @JsonIgnore
    private List<BookToBasketItem> bookToBasketItemList; //make this class, done

    @ManyToOne
    @JoinColumn(name = "shopping_basket_id")
    private ShoppingBasket shoppingBasket;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<BookToBasketItem> getBookToBasketItemList() {
        return bookToBasketItemList;
    }

    public void setBookToBasketItemList(List<BookToBasketItem> bookToBasketItemList) {
        this.bookToBasketItemList = bookToBasketItemList;
    }

    public ShoppingBasket getShoppingBasket() {
        return shoppingBasket;
    }

    public void setShoppingBasket(ShoppingBasket shoppingBasket) {
        this.shoppingBasket = shoppingBasket;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


}

