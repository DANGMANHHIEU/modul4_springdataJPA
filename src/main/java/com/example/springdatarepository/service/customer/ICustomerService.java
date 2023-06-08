package com.example.springdatarepository.service.customer;

import com.example.springdatarepository.model.Customer;
import com.example.springdatarepository.model.Province;
import com.example.springdatarepository.service.IGeneralService;

public interface ICustomerService extends IGeneralService<Customer> {

    Iterable<Customer> findByProvince(Province province);
}
