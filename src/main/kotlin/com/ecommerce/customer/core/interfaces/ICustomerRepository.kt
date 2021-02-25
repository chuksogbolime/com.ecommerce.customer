package com.ecommerce.customer.core.interfaces

import com.ecommerce.customer.core.entities.Customer
import org.springframework.data.repository.CrudRepository

interface ICustomerRepository:CrudRepository<Customer, Long> {
}