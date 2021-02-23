package com.ecommerce.customer.api

import com.ecommerce.customer.core.interfaces.ICustomerRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/customer")
class CustomerController(private val customerRepository: ICustomerRepository){


}