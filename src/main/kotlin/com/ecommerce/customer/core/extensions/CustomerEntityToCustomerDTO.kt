package com.ecommerce.customer.core.extensions

import com.ecommerce.customer.core.entities.Customer
import com.ecommerce.customer.core.models.dtos.CustomerDTO
import com.ecommerce.customer.core.models.api.requests.CustomerApiRequest

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

fun CustomerApiRequest.toCustomerDTO() = CustomerDTO(
    name=name,
    lastName=lastName,
    street=street,
    houseNumber=houseNumber,
    phone=phone,
    id=null
)