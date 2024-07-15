package com.example.assignment.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
	info=@Info(
		title="Product API",
		description="Doing CRUD Operation",
		termsOfService="T&C",
		contact = @Contact(name="ABC",
							email="ab@gmail.com"),
		license=@License(name="abc"),
		version="API/v1"
		
		
	)
//	security=@SecurityRequirement(name="sec")
	
)
//@SecurityScheme(name="sec",
//in=SecuritySchemeIn.HEADER,
//type=SecuritySchemeType.HTTP,
//bearerFormat="Bearer")
public class OpenApiConfig {

}