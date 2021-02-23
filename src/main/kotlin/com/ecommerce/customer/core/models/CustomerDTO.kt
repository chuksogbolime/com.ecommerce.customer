package com.ecommerce.customer.core.models

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

data class CustomerDTO(
    val name:String,
    val lastName: String,
    val street: String,
    val houseNumber: String,
    val phone: String,
    val id:Long?=null
)