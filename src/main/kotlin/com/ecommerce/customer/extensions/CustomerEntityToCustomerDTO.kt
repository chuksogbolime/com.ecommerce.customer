package com.ecommerce.customer.extensions

import com.ecommerce.customer.core.entities.Customer
import com.ecommerce.customer.core.models.CustomerDTO

fun Customer.toCustomerDTO() = CustomerDTO(
    name=name,
    lastName=lastName,
    street=street,
    houseNumber=houseNumber,
    phone=phone,
    id=id
)

fun CustomerDTO.toCustomer() = Customer(
    name=name,
    lastName=lastName,
    street=street,
    houseNumber=houseNumber,
    phone=phone,
    id=id
)