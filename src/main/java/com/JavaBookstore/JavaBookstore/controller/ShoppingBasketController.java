package com.JavaBookstore.JavaBookstore.controller;


import com.JavaBookstore.JavaBookstore.domain.BasketItem;
import com.JavaBookstore.JavaBookstore.domain.Book;
import com.JavaBookstore.JavaBookstore.domain.ShoppingBasket;
import com.JavaBookstore.JavaBookstore.domain.User;

import com.JavaBookstore.JavaBookstore.service.BasketItemService;
import com.JavaBookstore.JavaBookstore.service.BookService;
import com.JavaBookstore.JavaBookstore.service.ShoppingBasketService;
import com.JavaBookstore.JavaBookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

import static java.lang.Integer.parseInt;

@Controller
@RequestMapping("/shoppingBasket")
public class ShoppingBasketController {

    @Autowired
    private UserService userService;

    @Autowired
    private BasketItemService basketItemService;

    @Autowired
    private BookService bookService;

    @Autowired
    private ShoppingBasketService shoppingBasketService;

    @RequestMapping("/basket")
    public String shoppingBasket(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        ShoppingBasket shoppingBasket = user.getShoppingBasket();  //need to make method, done

        List<BasketItem> basketItemList = basketItemService.findByShoppingBasket(shoppingBasket);
        //need to make basketitemservice interface, done
        //then make basketitemservice impl, done
        //then make basketitemrepository

        shoppingBasketService.updateShoppingBasket(shoppingBasket); //need to make shoppingbasketservice interface

        model.addAttribute("basketItemList", basketItemList);
        model.addAttribute("shoppingBasket", shoppingBasket);

        return "shoppingBasket";
    }

    @RequestMapping("/addItem")
    public String addItem(@ModelAttribute("book") Book book, @ModelAttribute("qty") String qty, Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        book = bookService.findById(book.getId());

        if (Integer.parseInt(qty) > book.getInStockNumber()) {

            model.addAttribute("notEnoughStock", true);
            return "forward:/detailBook?id=" + book.getId();
            //or could use, this goes through back end,
            //return "redirect:/detailBook?id=" + book.getId();
        }

            BasketItem basketItem = basketItemService.addBookToBasketItem(book, user, parseInt(qty));
            model.addAttribute("addBookSuccess", true);

            return "forward:/detailBook?id=" +book.getId();

    }

}
