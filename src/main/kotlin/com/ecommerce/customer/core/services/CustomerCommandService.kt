package com.ecommerce.customer.core.services

import com.ecommerce.customer.core.extensions.toCustomer
import com.ecommerce.customer.core.extensions.toCustomerDTO
import com.ecommerce.customer.core.interfaces.ICustomerCommandService
import com.ecommerce.customer.core.interfaces.ICustomerRepository
import com.ecommerce.customer.core.models.dtos.CustomerDTO
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CustomerCommandService(private val customerRepository: ICustomerRepository): ICustomerCommandService {

    override fun addCustomer(customerDTO: CustomerDTO): Triple<CustomerDTO?,Boolean,String> {
        return try {
            Triple(customerRepository.save(customerDTO.toCustomer()).toCustomerDTO(), true, "Success")
        }catch (ex:Exception){
            Triple(null, false, ex.toString())
        }

    }

}