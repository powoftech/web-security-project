package com.example.customer.Controller;

import com.example.library.dto.ProductDto;
import com.example.library.model.Customer;
import com.example.library.model.ShoppingCart;
import com.example.library.service.CustomerService;
import com.example.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerService customerService;
    @RequestMapping(value={"/home","/"},method = RequestMethod.GET)
    public String getSampleProduct(Model model, HttpSession session)
    {
        Customer customer = (Customer) session.getAttribute("customer");
        List<ProductDto> productDtoList = productService.findsampleProductForCustomer();
        model.addAttribute("tittle","Manage Product");
        model.addAttribute("products",productDtoList);
        model.addAttribute("size",productDtoList.size());
        if (customer != null) {
            session.setAttribute("username", customer.getCustomerFirstName() + " " + customer.getCustomerLastName());
            ShoppingCart shoppingCart = customer.getCart();
            if (shoppingCart != null) {
                session.setAttribute("totalItems", shoppingCart.getCartTotalItems());
            }
            else{
                session.setAttribute("totalItems",'0');

            }
        }
        return "index";
    }
}
