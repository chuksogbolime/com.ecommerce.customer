package com.ecommerce.customer.core.models.api.response

import com.ecommerce.customer.core.models.dtos.CustomerDTO

class GenericResponse<T>(var responseCode:String, var responseDescription:String,
var data:T?) {
}
/*
fun getGenericResponse(t:Any?):GenericResponse<Any>{
    if(t !=  null){
        return GenericResponse<Any>("00", "Success", t)
    }
    return  return GenericResponse<Any>("999", "Failed", null)
}

 */