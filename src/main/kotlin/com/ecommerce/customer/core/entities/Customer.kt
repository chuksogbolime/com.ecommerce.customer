package com.ecommerce.customer.core.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Customer(
    val name:String,
    val lastName: String,
    val street: String,
    val houseNumber: String,
    val phone: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id:Long?=null
)