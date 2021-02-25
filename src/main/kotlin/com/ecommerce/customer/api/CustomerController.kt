package com.ecommerce.customer.api

import com.ecommerce.customer.core.extensions.toCustomerDTO
import com.ecommerce.customer.core.interfaces.ICustomerCommandService
import com.ecommerce.customer.core.interfaces.ICustomerQueryService
import com.ecommerce.customer.core.models.api.requests.CustomerApiRequest


import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customer")
class CustomerController(private val customerQueryService: ICustomerQueryService,
                         private val customerCommandService: ICustomerCommandService
){

    @GetMapping
    fun getCustomers():ResponseEntity<Any>
    {
        val result = customerQueryService.getCustomers().toList()
        //return ResponseEntity.ok(result)
        return if(result.isNotEmpty()) ResponseEntity.ok(result) else ResponseEntity.badRequest().body("Could not fetch data")
    }


    @PostMapping
    fun addCustomer(@Validated @RequestBody customer: CustomerApiRequest):ResponseEntity<Any>{
        val result=customerCommandService.addCustomer(customer.toCustomerDTO())
        return if(result.second)
            ResponseEntity.ok(result.first!!)
        else
            ResponseEntity.badRequest().body("Error saving customer")
    }

    @PutMapping("/{id}")
    fun updateCustomer(@PathVariable(value = "id") id: Long,
        @Validated @RequestBody customer: CustomerApiRequest):ResponseEntity<Any>{

        val result=customerCommandService.updateCustomer(id, customer.toCustomerDTO())
        return if(result.second)
            ResponseEntity.ok(result.first!!)
        else
            ResponseEntity.badRequest().body("Error updating customer")
    }


}