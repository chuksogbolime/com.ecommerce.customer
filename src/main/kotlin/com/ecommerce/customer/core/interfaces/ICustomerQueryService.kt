package com.ecommerce.customer.core.interfaces

import com.ecommerce.customer.core.models.dtos.CustomerDTO

interface ICustomerQueryService {
    fun getCustomers():Iterable<CustomerDTO>
}