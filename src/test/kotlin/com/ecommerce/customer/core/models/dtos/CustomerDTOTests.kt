package com.ecommerce.customer.core.models.dtos

import com.ecommerce.customer.core.helpers.buildCustomerDTO
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CustomerDTOTests {

    @Test
    fun `set CustomerDTO values should be returned exactly as passed`(){
        val customer = buildCustomerDTO()
        assertAll(
                    { Assertions.assertEquals(customer.houseNumber, "90")},
                    {Assertions.assertEquals(customer.id, 2)},
                    {Assertions.assertEquals(customer.lastName, "MylastName")},
                    {Assertions.assertEquals(customer.name, "MyName")},
                    {Assertions.assertEquals(customer.phone, "78373837")},
                    {Assertions.assertEquals(customer.street, "My Street")}
                )
    }


}