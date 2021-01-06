package com.raczkowski.springintro.controller;

import com.raczkowski.springintro.dto.CustomerDto;
import com.raczkowski.springintro.dto.OrderDto;
import com.raczkowski.springintro.exception.CustomerNotFoundException;
import com.raczkowski.springintro.exception.OrderNotFoundException;
import com.raczkowski.springintro.service.CustomerService;
import com.raczkowski.springintro.util.SortType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1")
public class CustomersController {

    public CustomerService customerService;

    public CustomersController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<CustomerDto> getCustomersWithQuery(@RequestParam(required = false) String sortBy) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(authentication);

        return sortBy != null ?
                customerService.getSortedCustomers(SortType.valueOf(sortBy)) :
                customerService.getCustomers();
    }

    @GetMapping("/customers/{id}")
    public CustomerDto getCustomer(@PathVariable String id) {
        return customerService.getCustomerForId(Long.valueOf(id));
    }

    @GetMapping("/customers/{id}/orders")
    public List<OrderDto> getOrdersForCustomer(@PathVariable String id) {
        return customerService.getOrdersForCustomer(Long.valueOf(id));
    }

    @GetMapping("/customers/{userId}/orders/{orderId}")
    public OrderDto getOrderForCustomer(@PathVariable String userId,
                                        @PathVariable String orderId) {
        return customerService.getSpecificOrderForGivenCustomer(Long.valueOf(userId), Long.valueOf(orderId));
    }

    @PostMapping("/customers")
    @ResponseStatus(CREATED)
    public void addCustomer(@RequestBody CustomerDto customerDto) {
        customerService.addCustomer(customerDto);
    }

    @DeleteMapping("/customers/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteCustomer(@PathVariable String id) {
        customerService.removeCustomer(Long.valueOf(id));
    }

    @PutMapping("/customers/{id}")
    @ResponseStatus
    public void updateCustomer(@PathVariable String id, @RequestBody CustomerDto customerDto) {
        customerService.updateCustomer(Long.valueOf(id), customerDto);
    }

    @ExceptionHandler({CustomerNotFoundException.class, OrderNotFoundException.class})
    public ResponseEntity<String> handleNotFoundExceptions(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), NOT_FOUND);
    }

}
