package com.ecommerce.customer.core.interfaces

import com.ecommerce.customer.core.models.dtos.CustomerDTO


interface ICustomerCommandService {
    fun addCustomer(customerDTO: CustomerDTO): Triple<CustomerDTO?,Boolean,String>
}