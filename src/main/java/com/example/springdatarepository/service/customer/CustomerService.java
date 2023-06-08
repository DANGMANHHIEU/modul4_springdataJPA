package com.example.springdatarepository.service.customer;

import com.example.springdatarepository.model.Customer;
import com.example.springdatarepository.model.Province;
import com.example.springdatarepository.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomerService implements ICustomerService{
    @Autowired
    ICustomerRepository customerRepository;
    @Override
    public Iterable findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void save(Customer customer) {
          customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
     customerRepository.deleteById(id);
    }

    @Override
    public Iterable<Customer> findByProvince(Province province) {
        return customerRepository.findAllByProvince(province);
    }
}
