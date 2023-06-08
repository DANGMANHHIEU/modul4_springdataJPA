package com.example.springdatarepository.controller;

import com.example.springdatarepository.model.Customer;
import com.example.springdatarepository.model.Province;
import com.example.springdatarepository.service.customer.CustomerService;
import com.example.springdatarepository.service.province.IProvinceService;
import com.example.springdatarepository.service.province.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller

public class ProvinceController {
    @Autowired
    private IProvinceService provinceService;

    @GetMapping("/provinces")
    public ModelAndView listProvinces() {
        Iterable<Province> provinces = provinceService.findAll();
        ModelAndView modelAndView = new ModelAndView("/province/list");
        modelAndView.addObject("provinces", provinces);
        return modelAndView;
    }

    @GetMapping("/create-province")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("/province/create");
        modelAndView.addObject("province",new Province());
        return modelAndView;
    }

    @PostMapping("/create-province")
    public ModelAndView saveProvince(@ModelAttribute("province") Province province){
        provinceService.save(province);
        ModelAndView modelAndView= new ModelAndView("/province/create");
        modelAndView.addObject("province",new Province());
        modelAndView.addObject("message", "New province created successfully");
        return modelAndView;
    }

    @GetMapping("edit-province/{id}")
    public ModelAndView edit(@PathVariable Long id){
        Optional<Province> province= provinceService.findById(id);
        if (province.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/province/edit");
            modelAndView.addObject("province", province.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }

    }

    @PostMapping("/edit-province")
    public ModelAndView update(@ModelAttribute("province") Province province){
        provinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("/province/edit");
        modelAndView.addObject("province", province);
        modelAndView.addObject("message", "Province updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-province/{id}")
    public ModelAndView delete(@PathVariable Long id){
        Optional<Province> province = provinceService.findById(id);
        provinceService.remove(province.get().getId());
       ModelAndView modelAndView = new ModelAndView("redirect:/provinces");
       return modelAndView;
    }

 @Autowired
    private ProvinceService provinceServices;
    @Autowired
    private CustomerService customerServices;
    @GetMapping("/view-province/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id){
        Optional<Province> provinceOptional = provinceServices.findById(id);
        if(!provinceOptional.isPresent()){
            return new ModelAndView("/error.404");
        }

        Iterable<Customer> customers = customerServices.findByProvince(provinceOptional.get());

        ModelAndView modelAndView = new ModelAndView("/province/view");
        modelAndView.addObject("province", provinceOptional.get());
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }


}