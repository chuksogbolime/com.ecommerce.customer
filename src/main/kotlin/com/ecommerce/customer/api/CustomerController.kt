package com.ecommerce.customer.api

import com.ecommerce.customer.core.extensions.toCustomerDTO
import com.ecommerce.customer.core.interfaces.ICustomerCommandService
import com.ecommerce.customer.core.interfaces.ICustomerQueryService
import com.ecommerce.customer.core.models.api.requests.CustomerApiRequest
import org.springframework.http.HttpStatus


import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/customer")
class CustomerController(private val customerQueryService: ICustomerQueryService,
                         private val customerCommandService: ICustomerCommandService
){
    val rootLink = "/api/customer"
    @GetMapping
    fun getCustomers():ResponseEntity<Any>
    {
        val result = customerQueryService.getCustomers().toList()
        return if(result.isNotEmpty()) ResponseEntity.ok(result) else ResponseEntity<Any>(HttpStatus.NO_CONTENT)
    }


    @PostMapping
    fun addCustomer(@Validated @RequestBody customer: CustomerApiRequest):ResponseEntity<Any>{
        val result=customerCommandService.addCustomer(customer.toCustomerDTO())

        return if(result.second){
            val urlString = "$rootLink/${result.first?.id}"
            ResponseEntity.created(URI.create(urlString)).body(result.first!!)
        }
        else
            ResponseEntity.badRequest().body(null)
    }

    @PutMapping("/{id}")
    fun updateCustomer(@PathVariable(value = "id") id: Long,
        @Validated @RequestBody customer: CustomerApiRequest):ResponseEntity<Any>{

        val result=customerCommandService.updateCustomer(id, customer.toCustomerDTO())

        return if(result.second)
            ResponseEntity<Any>(HttpStatus.NO_CONTENT)
        else
            ResponseEntity.badRequest().body(null)
    }

    @GetMapping("/{id}")
    fun getCustomerById(@PathVariable(value = "id") id: Long):ResponseEntity<Any>{
        val result=customerQueryService.getCustomerById(id)
        return if(result !=null) ResponseEntity.ok(result) else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable(value = "id") id: Long):ResponseEntity<Any>{
        val result=customerCommandService.deleteCustomer(id)
        return if(result.first) ResponseEntity<Any>(HttpStatus.OK) else ResponseEntity.notFound().build()
    }

}