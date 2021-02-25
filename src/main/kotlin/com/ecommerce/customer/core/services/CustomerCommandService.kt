package com.ecommerce.customer.core.services

import com.ecommerce.customer.core.entities.Customer
import com.ecommerce.customer.core.extensions.toCustomer
import com.ecommerce.customer.core.extensions.toCustomerDTO
import com.ecommerce.customer.core.interfaces.ICustomerCommandService
import com.ecommerce.customer.core.interfaces.ICustomerRepository
import com.ecommerce.customer.core.models.dtos.CustomerDTO
import org.springframework.data.repository.findByIdOrNull
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

    override fun updateCustomer(id:Long, customerDTO: CustomerDTO): Triple<CustomerDTO?,Boolean,String>{

        return try {
            customerRepository.findById(id).map { exiting ->
               val update = exiting.copy(
                   houseNumber = customerDTO.houseNumber,
                   street = customerDTO.street,
                   lastName = customerDTO.lastName,
                   name = customerDTO.name,
                   phone = customerDTO.phone
               )
               Triple(customerRepository.save(update).toCustomerDTO(), true,"Success")
           }.orElse(
               Triple(customerDTO,false,"Customer with Id:$id, does not exist")
           )

        } catch (ex: java.lang.Exception) {
            Triple(null, false, ex.toString())
        }

    }
}