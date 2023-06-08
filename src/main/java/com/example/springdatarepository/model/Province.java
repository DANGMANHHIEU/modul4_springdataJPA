package com.example.springdatarepository.model;



import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "provinces")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(targetEntity = Customer.class)
    private List<Customer> customerList;

    public Province() {
    }

    public Province(String name, List<Customer> customerList) {
        this.name = name;
        this.customerList = customerList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
    @Override
    public String toString() {
        return String.format("Province[id=%d, name='%s']", id, name);
    }

}
