package br.com.zupacademy.autores.feign

import br.com.zupacademy.autores.dto.EnderecoResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client

@Client("\${endereco.client.url}")
interface EnderecoClient {

    @Get("/{cep}/json")
    fun searchInJson(@PathVariable cep: String): HttpResponse<EnderecoResponse>

    @Get("/{cep}/xml", consumes = [MediaType.APPLICATION_XML])
    fun searchInXml(@PathVariable cep: String): HttpResponse<EnderecoResponse>
}