package springdemo.service;

import java.util.List;

import springdemo.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomers(int sortField);

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int id);

	public void deleteCustomer(int id);

	public List<Customer> searchCustomers(String searchName);
}
