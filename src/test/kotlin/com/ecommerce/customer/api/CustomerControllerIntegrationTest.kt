package com.ecommerce.customer.api

import com.ecommerce.customer.core.extensions.toCustomer
import com.ecommerce.customer.core.helpers.buildCustomerDTO
import com.ecommerce.customer.core.interfaces.ICustomerCommandService
import com.ecommerce.customer.core.interfaces.ICustomerQueryService
import com.ecommerce.customer.core.interfaces.ICustomerRepository
import com.ecommerce.customer.core.models.dtos.CustomerDTO
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put

//@WebMvcTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CustomerControllerIntegrationTest
    (@Autowired val mockMvc: MockMvc,
@Autowired val customerRepository: ICustomerRepository){

    @RelaxedMockK
    var queryService: ICustomerQueryService = mockk()
    @RelaxedMockK
    var commandService: ICustomerCommandService = mockk()

    @Test
    fun `getCustomer should return Http Status OK`(){
        val customerList = ArrayList<CustomerDTO>()
        for(i in 1..3){
            val customer = buildCustomerDTO()
            customerList.add(customer)
            customerRepository.save(customer.toCustomer())
        }

        mockMvc.get("/api/customer").andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { customerList }
        }

    }

    @Test
    fun `getCustomer should return Http Status BadRequest`(){
        customerRepository.deleteAll()
       mockMvc.get("/api/customer").andExpect {
            status { isBadRequest() }
            content { "Could not fetch data" }
        }

    }

    @Test
    fun `addCustomer should return Http Status OK`(){

        val customer = buildCustomerDTO()
        mockMvc.post("/api/customer"){
            content = ObjectMapper().writeValueAsString(customer)
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { ObjectMapper().writeValueAsString(customer) }
        }

    }

    @Test
    fun `addCustomer should return Http Status BadRequest`(){
        mockMvc.post("/api/customer").andExpect {
            status { isBadRequest() }
            content { "Error saving customer" }
        }

    }

    @Test
    fun `updateCustomer should return Http Status OK`(){

        val customer = buildCustomerDTO()
        val saved= customerRepository.save(customer.toCustomer())
        mockMvc.put("/api/customer/${saved.id}"){
            content = ObjectMapper().writeValueAsString(customer)
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { ObjectMapper().writeValueAsString(customer) }
        }

    }

    @Test
    fun `updateCustomer should return Http Status BadRequest`(){
        mockMvc.put("/api/customer/1").andExpect {
            status { isBadRequest() }
            content { "Error saving customer" }
        }

    }

    @Test
    fun `getCustomerById should return Http Status OK`(){

        val customer = buildCustomerDTO()
        val saved= customerRepository.save(customer.toCustomer())
        mockMvc.get("/api/customer/${saved.id}"){
            content = ObjectMapper().writeValueAsString(customer)
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { ObjectMapper().writeValueAsString(customer) }
        }

    }

    @Test
    fun `getCustomerById should return Http Status BadRequest`(){
        val expectedId = Long.MAX_VALUE
        mockMvc.get("/api/customer/${expectedId}").andExpect {
            status { isBadRequest() }
            content { "Customer with Id:$expectedId does not existcustomer" }
        }

    }
}