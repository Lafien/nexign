package com.nefedov.nexigntestapplication.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "NexignTestApplication",
                description = "Test developer application for Nexign",
                contact = @Contact(
                        name = "Aleksandr Nefedov",
                        email = "lifaenka@list.ru",
                        url = "https://t.me/Lafien"
                )
        )
)
public class SwaggerConfiguration {
}
