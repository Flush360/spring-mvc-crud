package springdemo.dao;

import java.util.List;

import springdemo.entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers(int sortField);

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int id);

	public void deleteCustomer(int id);

	public List<Customer> searchCustomers(String searchName);

}
