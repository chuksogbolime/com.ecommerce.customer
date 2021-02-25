package com.ecommerce.customer.core.models.api.response

import com.ecommerce.customer.core.models.dtos.CustomerDTO
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class GenericResponseTest {

    @Test
    fun `GenericResponse should return set values`(){
        val responseData = CustomerDTO("Name", "LName","Street", "90", "998999",9)
        val response = GenericResponse<CustomerDTO>("00","Success", responseData)
        assertAll(
            {Assertions.assertTrue(response.data is CustomerDTO)},
            {Assertions.assertEquals(response.responseCode, "00")},
            {Assertions.assertEquals(response.responseDescription, "Success")}
        )

    }
/*
    @Test
    fun `getGenericResponse should return response code 00 for success when T is not null`(){
        val responseData = CustomerDTO("Name", "LName","Street", "90", "998999",9)
        val expected = "00"
        val result = getGenericResponse(responseData)
        Assertions.assertEquals(result.responseCode, expected)
    }

    @Test
    fun `getGenericResponse should return response code 999 for success when T is null`(){
        //val responseData = CustomerDTO("Name", "LName","Street", "90", "998999",9)
        val expected = "999"
        val result = getGenericResponse(null)
        Assertions.assertEquals(result.responseCode, expected)
    }

 */
}