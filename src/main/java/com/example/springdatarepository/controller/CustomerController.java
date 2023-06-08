package com.example.springdatarepository.controller;

import com.example.springdatarepository.model.Customer;
import com.example.springdatarepository.model.Province;
import com.example.springdatarepository.service.customer.ICustomerService;
import com.example.springdatarepository.service.province.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CustomerController {
    @Autowired
    IProvinceService provinceService;
    @Autowired
    ICustomerService customerService;

    @ModelAttribute("provinces")
    public Iterable<Province>provinces(){
        return provinceService.findAll();
    }

    @GetMapping("/customer")
    public ModelAndView showList(){
       Iterable<Customer>customers= customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customer",customers);
        return modelAndView;
    }

    @GetMapping("/create-customer")
    public ModelAndView showCreate(){
     ModelAndView modelAndView = new ModelAndView("/customer/create");
     modelAndView.addObject("customer",new Customer());
     return modelAndView;
    }

    @PostMapping("/create-customer")
    public ModelAndView saveProvince(@ModelAttribute("customer") Customer customer){
       customerService.save(customer);
        ModelAndView modelAndView= new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message", "New customer created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-customer/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/customer/edit");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("edit-customer")
    public ModelAndView update(@ModelAttribute("customer") Customer customer){
      customerService.save(customer);
      ModelAndView modelAndView = new ModelAndView("/customer/edit");
      modelAndView.addObject("customer",new Customer());
      modelAndView.addObject("message", "Customer updated successfully");
      return modelAndView;
    }

    @GetMapping("/delete-customer/{id}")
    public ModelAndView delete(@PathVariable Long id){
   Optional<Customer>customer=customerService.findById(id);
customerService.remove(customer.get().getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/customer");
        return modelAndView;
    }

}
