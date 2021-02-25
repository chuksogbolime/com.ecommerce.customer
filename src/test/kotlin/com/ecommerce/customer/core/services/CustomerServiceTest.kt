package com.ecommerce.customer.core.services

import com.ecommerce.customer.core.entities.Customer
import com.ecommerce.customer.core.extensions.toCustomer
import com.ecommerce.customer.core.helpers.buildCustomer
import com.ecommerce.customer.core.helpers.buildCustomerDTO
import com.ecommerce.customer.core.interfaces.ICustomerCommandService
import com.ecommerce.customer.core.interfaces.ICustomerQueryService
import com.ecommerce.customer.core.interfaces.ICustomerRepository
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class CustomerServiceTest
@Autowired constructor(val customerRepository: ICustomerRepository){

    @Test
    fun `addCustomer should add a customer object to db and return true, customer not null and Success message`(){

        val customerDTO = buildCustomerDTO()

        val sut : ICustomerCommandService = CustomerCommandService(customerRepository)


        val result = sut.addCustomer(customerDTO)
        assertAll(
            { Assertions.assertTrue(result.second)},
            { Assertions.assertTrue(result.first !=null)},
            { Assertions.assertEquals(result.third, "Success") }

        )
    }

    @Test
    fun `addCustomer should throw an exception on add a customer object to db and return false, null and exception message`(){

        val customerDTO = buildCustomerDTO()
        @RelaxedMockK
        var customerRepositoryMock = mockk<ICustomerRepository>()
        every { customerRepositoryMock.save(customerDTO.toCustomer()) } throws Exception("Exception")
        val sut : ICustomerCommandService = CustomerCommandService(customerRepositoryMock)


        val result = sut.addCustomer(customerDTO)
        assertAll(
            { Assertions.assertFalse(result.second)},
            { Assertions.assertTrue(result.first ==null)}

        )
    }



    @Test
    fun `addCustomer should add a customer object to db and return customer object with id not null and greater than 0`(){
        val customerDTO = buildCustomerDTO()
        val _sut : ICustomerCommandService = CustomerCommandService(customerRepository)

        val result = _sut.addCustomer(customerDTO)
        val actual = result.first?.id
        assertAll(

            { Assertions.assertTrue(result.first !=null)},
            { Assertions.assertTrue(actual !=null) },
            { Assertions.assertTrue(0 < actual!!) }

            )
    }



    @Test
    fun `getCustomers should return a list of CustomerDTO`(){
        val customerList = ArrayList<Customer>()
        for(i in 1..3){
            val customer = buildCustomer()
            customerList.add(customer)
            customerRepository.save(customer)
        }
        //customerRepository.saveAll(customerList)

        val sut : ICustomerQueryService = CustomerQueryService(customerRepository)

        val result = sut.getCustomers()
        Assertions.assertTrue(result.count() == 3)
    }
}