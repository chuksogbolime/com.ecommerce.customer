package com.ecommerce.customer.core.extensions

import com.ecommerce.customer.core.helpers.buildCustomer
import com.ecommerce.customer.core.helpers.buildCustomerApiRequest
import com.ecommerce.customer.core.helpers.buildCustomerDTO
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CustomerEntityToCustomerDTOTest {

    @Test
    fun `toCustomerDTO extension on Customer should return CustomerDTO object`(){
        val customer = buildCustomer()
        val result = customer.toCustomerDTO()
        assertAll(
            //{Assertions.assertTrue(result is CustomerDTO)},
            {Assertions.assertEquals(customer.name, result.name)},
            {Assertions.assertEquals(customer.houseNumber, result.houseNumber)},
            {Assertions.assertEquals(customer.id, result.id)},
            {Assertions.assertEquals(customer.lastName, result.lastName)},
            {Assertions.assertEquals(customer.phone, result.phone)},
            {Assertions.assertEquals(customer.street, result.street)}
        )

    }

    @Test
    fun `toCustomer extension on CustomerDTO should return Customer object`(){
        val customer = buildCustomerDTO()
        val result = customer.toCustomer()
        assertAll(
            //{Assertions.assertTrue(result is Customer)},
            {Assertions.assertEquals(customer.name, result.name)},
            {Assertions.assertEquals(customer.houseNumber, result.houseNumber)},
            {Assertions.assertEquals(customer.id, result.id)},
            {Assertions.assertEquals(customer.lastName, result.lastName)},
            {Assertions.assertEquals(customer.phone, result.phone)},
            {Assertions.assertEquals(customer.street, result.street)}
        )

    }

    @Test
    fun `toCustomerDTO extension on CustomerApiRequest should return CustomerDTO object`(){
        val customer = buildCustomerApiRequest()
        val result = customer.toCustomerDTO()
        assertAll(
            //{Assertions.assertTrue(result is CustomerDTO)},
            {Assertions.assertEquals(customer.name, result.name)},
            {Assertions.assertEquals(customer.houseNumber, result.houseNumber)},
            {Assertions.assertEquals(customer.lastName, result.lastName)},
            {Assertions.assertEquals(customer.phone, result.phone)},
            {Assertions.assertEquals(customer.street, result.street)}
        )

    }
}