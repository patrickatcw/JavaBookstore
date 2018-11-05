package com.JavaBookstore.JavaBookstore.controller;

import com.JavaBookstore.JavaBookstore.domain.*;
import com.JavaBookstore.JavaBookstore.service.*;
import com.JavaBookstore.JavaBookstore.utility.USConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
public class CheckoutController {

    private ShippingAddress shippingAddress = new ShippingAddress();
    private BillingAddress billingAddress = new BillingAddress();
    private Payment payment = new Payment();

    @Autowired
    private UserService userService;

    @Autowired
    private BasketItemService basketItemService;

    @Autowired
    private ShippingAddressService shippingAddressService;      //make the interface, done

    @Autowired
    private BillingAddressService billingAddressService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserShippingService userShippingService;

    @RequestMapping("/checkout")
    public String checkout(
            @RequestParam("id") Long basketId,
            @RequestParam(value = "missingRequiredField", required = false) boolean missingRequiredField,
            Model model, Principal principal
    ) {
        User user = userService.findByUsername(principal.getName());

        if (basketId!=user.getShoppingBasket().getId()) {
            return "badRequestPage";
        }

        List<BasketItem> basketItemList = basketItemService.findByShoppingBasket(user.getShoppingBasket());

        if (basketItemList.size()==0) {
            model.addAttribute("emptyBasket", true);
            return "forward:/shoppingBasket/basket";
        }

        for (BasketItem basketItem : basketItemList) {
            if (basketItem.getBook().getInStockNumber() < basketItem.getQty()) {
                model.addAttribute("notEnoughStock", true);
                return "forward:/shoppingBasket/basket";
            }
        }

        List<UserShipping> userShippingList = user.getUserShippingList();
        List<UserPayment> userPaymentList = user.getUserPaymentList();

        model.addAttribute("userShippingList", userShippingList);
        model.addAttribute("userPaymentList", userPaymentList);

        if (userPaymentList.size()==0) {
            model.addAttribute("emptyPaymentList", true);
        } else {
            model.addAttribute("emptyPaymentList", false);
        }

        if (userShippingList.size()==0) {
            model.addAttribute("emptyShippingList", true);
        } else {
            model.addAttribute("emptyShippingList", false);
        }

        ShoppingBasket shoppingBasket = user.getShoppingBasket();

        for (UserShipping userShipping : userShippingList) {
            if (userShipping.isUserShippingDefault()) {
                shippingAddressService.setByUserShipping(userShipping, shippingAddress);
            }
        }

        for (UserPayment userPayment : userPaymentList) {
            if (userPayment.isDefaultPayment()) {
                paymentService.setByUserPayment(userPayment, payment);      //make paymentService interface
                billingAddressService.setByUserBilling(userPayment.getUserBilling(), billingAddress);
                //make billingAddressService interface

            }
        }

        model.addAttribute("shippingAddress", shippingAddress);
        model.addAttribute("payment", payment);
        model.addAttribute("billingAddress", billingAddress);
        model.addAttribute("basketItemList", basketItemList);
        model.addAttribute("shoppingBasket", user.getShoppingBasket());

        List<String> stateList = USConstants.listOfUSStatesCode;
        Collections.sort(stateList);
        model.addAttribute("stateList", stateList);

        model.addAttribute("classActiveShipping", true);

        if (missingRequiredField) {
            model.addAttribute("missingRequiredField", true);
        }

        return "checkout";

    }

    @RequestMapping("/setShippingAddress")
    public String setShippingAddress(
            @RequestParam("userShippingId") Long userShippingId,
            Principal principal, Model model
    ) {
        User user = userService.findByUsername(principal.getName());
        UserShipping userShipping = userShippingService.findById(userShippingId);

        if (userShipping.getUser().getId()!=user.getId()) {
            return "badRequestPage";
        } else {
            shippingAddressService.setByUserShipping(userShipping, shippingAddress);

            List<BasketItem> basketItemList = basketItemService.findByShoppingBasket(user.getShoppingBasket());

            BillingAddress billingAddress = new BillingAddress();

            model.addAttribute("shippingAddress", shippingAddress);
            model.addAttribute("payment", payment);
            model.addAttribute("billingAddress", billingAddress);
            model.addAttribute("basketItemList", basketItemList);
            model.addAttribute("shoppingBasket", user.getShoppingBasket());

            List<String> stateList = USConstants.listOfUSStatesCode;
            Collections.sort(stateList);
            model.addAttribute("stateList", stateList);

            List<UserShipping> userShippingList = user.getUserShippingList();
            List<UserPayment> userPaymentList = user.getUserPaymentList();

            model.addAttribute("userShippingList", userShippingList);
            model.addAttribute("userPaymentList", userPaymentList);

            model.addAttribute("shippingAddress", shippingAddress);

            model.addAttribute("classActiveShipping", true);

            if (userPaymentList.size()==0) {
                model.addAttribute("emptyPaymentList", true);
            } else {
                model.addAttribute("emptyPaymentList", false);
            }

            model.addAttribute("emptyShippingList", false);


            return "checkout";
        }

    }

}

