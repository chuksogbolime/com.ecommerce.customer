package com.ecommerce.customer.core.models.api.requests

import com.ecommerce.customer.core.helpers.buildCustomerApiRequest
import com.ecommerce.customer.core.models.dtos.CustomerDTO
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CustomerApiRequestTest {
    @Test
    fun `set CustomerApiRequest values should be returned exactly as passed`(){
        val customer = buildCustomerApiRequest()
        assertAll(
            { Assertions.assertEquals(customer.houseNumber, "90")},
            { Assertions.assertEquals(customer.lastName, "MylastName")},
            { Assertions.assertEquals(customer.name, "MyName")},
            { Assertions.assertEquals(customer.phone, "78373837")},
            { Assertions.assertEquals(customer.street, "My Street")}
        )
    }


}