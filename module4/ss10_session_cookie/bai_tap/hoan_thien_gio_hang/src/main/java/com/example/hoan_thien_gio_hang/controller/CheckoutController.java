package com.example.hoan_thien_gio_hang.controller;

import com.example.hoan_thien_gio_hang.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private ICartService cartService;

    @GetMapping
    public String showCheckoutPage(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("total", cartService.getTotal());
        return "checkout";
    }

    @PostMapping("/submit")
    public String submitOrder(RedirectAttributes redirectAttributes) {
        // Nếu cần lưu vào DB, xử lý ở đây
        cartService.clearCart();

        redirectAttributes.addFlashAttribute("successMessage", "Thanh toán thành công!");
        return "redirect:/products";
    }
}

