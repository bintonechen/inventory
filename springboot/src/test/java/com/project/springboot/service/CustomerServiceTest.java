/**
 * Author: Bintong
 * Date: 2024/7/29
 */

package com.project.springboot.service;

import com.project.springboot.entity.Customer;
import com.project.springboot.exception.ServiceException;
import com.project.springboot.mapper.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegister() {
        Customer customer = new Customer();
        customer.setCustomerEmail("test@example.com");
        customer.setCustomerContactNum("1234567890");

        when(customerMapper.selectByCustomerEmail(anyString())).thenReturn(null);
        when(customerMapper.selectByCustomerContactNum(anyString())).thenReturn(null);

        assertDoesNotThrow(() -> customerService.register(customer));
        verify(customerMapper, times(1)).insert(customer);
    }

    @Test
    public void testRegisterEmailExists() {
        Customer customer = new Customer();
        customer.setCustomerEmail("test@example.com");

        when(customerMapper.selectByCustomerEmail(anyString())).thenReturn(new Customer());

        ServiceException exception = assertThrows(ServiceException.class, () -> customerService.register(customer));
        assertEquals("Customer Email already exists. ", exception.getMessage());
    }

    @Test
    public void testDeleteCustomer() {
        Integer customerId = 1;
        customerService.deleteCustomer(customerId);
        verify(customerMapper, times(1)).delete(customerId);
    }

    @Test
    public void testBatchDeleteCustomer() {
        List<Integer> ids = Arrays.asList(1, 2, 3);
        customerService.batchDeleteCustomer(ids);
        verify(customerMapper, times(3)).delete(anyInt());
    }

    @Test
    public void testSelectAll() {
        List<Customer> customers = Arrays.asList(new Customer(), new Customer());
        when(customerMapper.selectAll()).thenReturn(customers);

        List<Customer> result = customerService.selectAll();
        assertEquals(2, result.size());
    }

    @Test
    public void testUpdateCustomer() {
        Customer customer = new Customer();
        customerService.updateCustomer(customer);
        verify(customerMapper, times(1)).update(customer);
    }
}
