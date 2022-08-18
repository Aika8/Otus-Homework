package ru.otus;


import java.util.Map;
import java.util.TreeMap;

public class CustomerService {
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    TreeMap<Customer, String> customers = new TreeMap<>();

    public Map.Entry<Customer, String> getSmallest() {
        Customer crutchCustomer = new Customer(customers.firstKey());
        return Map.entry(crutchCustomer, customers.get(crutchCustomer));
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Customer crutchCustomer = customers.higherKey(customer);
        return crutchCustomer != null ? Map.entry(new Customer(crutchCustomer), customers.get(crutchCustomer)) : null;
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }
}
