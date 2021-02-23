package com.ecommerce.customer.core.services


import com.ecommerce.customer.core.interfaces.ICustomerRepository
import com.ecommerce.customer.core.models.CustomerDTO
import com.ecommerce.customer.extensions.toCustomer
import com.ecommerce.customer.extensions.toCustomerDTO
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CustomerService(private val customerRepository: ICustomerRepository){

    fun getCustomers():Iterable<CustomerDTO>
        = customerRepository.findAll().map{it.toCustomerDTO()}

    fun addCustomer(customerDTO: CustomerDTO):ResponseEntity<CustomerDTO>
         = ResponseEntity.ok(customerRepository.save(customerDTO.toCustomer()).toCustomerDTO())

}