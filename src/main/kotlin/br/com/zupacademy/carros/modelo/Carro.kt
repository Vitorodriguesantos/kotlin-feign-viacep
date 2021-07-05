package br.com.zupacademy.carros.modelo

import br.com.zupacademy.carros.Placa
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
data class Carro(
    @field:NotBlank @field:Placa val placa: String,
    @field:NotBlank val modelo: String,
)