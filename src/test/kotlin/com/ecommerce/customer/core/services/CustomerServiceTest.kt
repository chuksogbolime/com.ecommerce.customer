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
            { Assertions.assertEquals("Success",result.third) }

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

    @Test
    fun `updateCustomer should update an existing record and return a Tripple with true`(){
        //Arrange
        val customer = buildCustomerDTO()
        val saved = customerRepository.save(customer.toCustomer())
        val sut : ICustomerCommandService = CustomerCommandService(customerRepository)
        //Act
        val result = sut.updateCustomer(saved.id!!, customer)

        //Assert
        assertAll(
            { Assertions.assertTrue(result.second)},
            { Assertions.assertTrue(result.first !=null)},
            { Assertions.assertEquals( "Success",result.third) }

        )

    }

    @Test
    fun `updateCustomer should fail for a record not found and return a Tripple with false`(){
        //Arrange
        val customer = buildCustomerDTO()
        val expectedId = Long.MAX_VALUE
        val sut : ICustomerCommandService = CustomerCommandService(customerRepository)
        //Act
        val result = sut.updateCustomer(expectedId, customer)

        //Assert
        assertAll(
            { Assertions.assertFalse(result.second)},
            { Assertions.assertEquals( "Customer with Id:$expectedId, does not exist",result.third) }

        )

    }

    @Test
    fun `updateCustomer should throw an exception on update object to db and return false, null and exception message`(){

        val customerDTO = buildCustomerDTO()
        @RelaxedMockK
        var customerRepositoryMock = mockk<ICustomerRepository>()
        every { customerRepositoryMock.save(customerDTO.toCustomer()) } throws Exception("Exception")
        val sut : ICustomerCommandService = CustomerCommandService(customerRepositoryMock)


        val result = sut.updateCustomer(0, customerDTO)
        assertAll(
            { Assertions.assertFalse(result.second)},
            { Assertions.assertTrue(result.first ==null)}

        )
    }

    @Test
    fun `getCustomerById should return found CustomerDTO`(){
        val customerList = ArrayList<Customer>()
        for(i in 1..3){
            customerList.add(customerRepository.save(buildCustomer()))
        }

        val sut : ICustomerQueryService = CustomerQueryService(customerRepository)

        val result = sut.getCustomerById(customerList.first().id!!)
        Assertions.assertTrue(result !=null)
    }

    @Test
    fun `getCustomerById should return null for not found customer`(){
        customerRepository.deleteAll()
        val sut : ICustomerQueryService = CustomerQueryService(customerRepository)

        val result = sut.getCustomerById(Long.MAX_VALUE)
        Assertions.assertTrue(result ==null)
    }
}