package com.JavaBookstore.JavaBookstore.controller;


import com.JavaBookstore.JavaBookstore.domain.BasketItem;
import com.JavaBookstore.JavaBookstore.domain.ShoppingBasket;
import com.JavaBookstore.JavaBookstore.domain.User;
import com.JavaBookstore.JavaBookstore.service.BasketItemService;
import com.JavaBookstore.JavaBookstore.service.ShoppingBasketService;
import com.JavaBookstore.JavaBookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/shoppingBasket")
public class ShoppingBasketController {

    @Autowired
    private UserService userService;

    @Autowired
    private BasketItemService basketItemService;

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

}
