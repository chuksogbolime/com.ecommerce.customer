package com.ecommerce.customer.core.services

import com.ecommerce.customer.core.extensions.toCustomerDTO
import com.ecommerce.customer.core.interfaces.ICustomerQueryService
import com.ecommerce.customer.core.interfaces.ICustomerRepository
import com.ecommerce.customer.core.models.dtos.CustomerDTO
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CustomerQueryService(private val customerRepository: ICustomerRepository): ICustomerQueryService {

    override fun getCustomers():Iterable<CustomerDTO>
            = customerRepository.findAll().map{it.toCustomerDTO()}

    override fun getCustomerById(id:Long):CustomerDTO?
        = customerRepository.findByIdOrNull(id)?.toCustomerDTO()
}