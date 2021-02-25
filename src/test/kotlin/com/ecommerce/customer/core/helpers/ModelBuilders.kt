package com.ecommerce.customer.core.helpers

import com.ecommerce.customer.core.entities.Customer
import com.ecommerce.customer.core.models.api.requests.CustomerApiRequest
import com.ecommerce.customer.core.models.dtos.CustomerDTO


fun buildCustomer()= Customer(
    "MyName",
    "MylastName",
    "My Street",
    "90",
    "78373837",
    0
)

fun buildCustomerDTO()= CustomerDTO(
    "MyName",
    "MylastName",
    "My Street",
    "90",
    "78373837",
    2
)

fun buildCustomerApiRequest()= CustomerApiRequest(
    "MyName",
    "MylastName",
    "My Street",
    "90",
    "78373837"

)