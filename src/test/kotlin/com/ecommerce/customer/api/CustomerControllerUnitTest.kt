package com.ecommerce.customer.api

import com.ecommerce.customer.core.entities.Customer
import com.ecommerce.customer.core.extensions.toCustomer
import com.ecommerce.customer.core.extensions.toCustomerDTO
import com.ecommerce.customer.core.helpers.buildCustomer
import com.ecommerce.customer.core.helpers.buildCustomerApiRequest
import com.ecommerce.customer.core.helpers.buildCustomerDTO
import com.ecommerce.customer.core.interfaces.ICustomerCommandService
import com.ecommerce.customer.core.interfaces.ICustomerQueryService
import com.ecommerce.customer.core.models.dtos.CustomerDTO
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus

//@WebMvcTest
class CustomerControllerUnitTest {

    @Test
    fun `getCustomers should return OK`(){
        val customerList = ArrayList<CustomerDTO>()
        for(i in 1..3){
            val customer = buildCustomerDTO()
            customerList.add(customer)

        }
        @RelaxedMockK
        val queryMock = mockk<ICustomerQueryService>()
        val commandMock = mockk<ICustomerCommandService>()

        val controller =CustomerController(queryMock,commandMock)
        every { queryMock.getCustomers() } returns customerList

        val result = controller.getCustomers()

        Assertions.assertTrue(result.statusCode==HttpStatus.OK)
    }

    @Test
    fun `getCustomers should return BadRequest`(){
        val customerList = ArrayList<CustomerDTO>()

        @RelaxedMockK
        val queryMock = mockk<ICustomerQueryService>()
        val commandMock = mockk<ICustomerCommandService>()

        val controller =CustomerController(queryMock,commandMock)
        every { queryMock.getCustomers() } returns customerList

        val result = controller.getCustomers()

        Assertions.assertTrue(result.statusCode==HttpStatus.BAD_REQUEST)
    }

    @Test
    fun `addCustomer should return OK`(){
        val customer = buildCustomerApiRequest()

        @RelaxedMockK
        val queryMock = mockk<ICustomerQueryService>()
        val commandMock = mockk<ICustomerCommandService>()

        val controller =CustomerController(queryMock,commandMock)
        every { commandMock.addCustomer(customer.toCustomerDTO()) } returns Triple(customer.toCustomerDTO(), true,"Success")

        val result = controller.addCustomer(customer)

        Assertions.assertTrue(result.statusCode==HttpStatus.OK)
    }

    @Test
    fun `addCustomer should return BadRequest`(){
        val customer = buildCustomerApiRequest()

        @RelaxedMockK
        val queryMock = mockk<ICustomerQueryService>()
        val commandMock = mockk<ICustomerCommandService>()

        val controller =CustomerController(queryMock,commandMock)
        every { commandMock.addCustomer(customer.toCustomerDTO()) } returns Triple(null, false,"")

        val result = controller.addCustomer(customer)

        Assertions.assertTrue(result.statusCode==HttpStatus.BAD_REQUEST)
    }


    @Test
    fun `updateCustomer should return OK`(){
        val customer = buildCustomerApiRequest()
        val expectedId=1L
        @RelaxedMockK
        val queryMock = mockk<ICustomerQueryService>()
        val commandMock = mockk<ICustomerCommandService>()

        val controller =CustomerController(queryMock,commandMock)
        every { commandMock.updateCustomer(expectedId, customer.toCustomerDTO()) } returns Triple(customer.toCustomerDTO(), true,"Success")

        val result = controller.updateCustomer(expectedId, customer)

        Assertions.assertTrue(result.statusCode==HttpStatus.OK)
    }

    @Test
    fun `updateCustomer should return BadRequest`(){
        val customer = buildCustomerApiRequest()
        val expectedId=1L
        @RelaxedMockK
        val queryMock = mockk<ICustomerQueryService>()
        val commandMock = mockk<ICustomerCommandService>()

        val controller =CustomerController(queryMock,commandMock)
        every { commandMock.updateCustomer(expectedId, customer.toCustomerDTO()) } returns Triple(null, false,"")

        val result = controller.updateCustomer(expectedId, customer)

        Assertions.assertTrue(result.statusCode==HttpStatus.BAD_REQUEST)
    }
}