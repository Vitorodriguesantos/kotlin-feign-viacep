package br.com.zupacademy.autores.controller

import br.com.zupacademy.autores.dto.EnderecoResponse
import br.com.zupacademy.autores.dto.NovoAutorRequest
import br.com.zupacademy.autores.feign.EnderecoClient
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import javax.inject.Inject

@MicronautTest
internal class CadastrarAutorControllerTest{

    @field:Inject
    lateinit var enderecoClient: EnderecoClient

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Test
    internal fun `Deve cadsatrar um novo autor`(){

        val novoAutorRequest = NovoAutorRequest(
            "Vitor R",
            "vitor@zup.com.br",
            "Developer",
            "38446-066",
            "43"
        )
        val enderecoResponse = EnderecoResponse(
            "38446-066",
            "Rua dos Rouxinois",
            "X",
            "Bosque",
            "Araguari",
            "MG",
            "XX",
            "XXX",
            "34",
            "GG"
        )

        Mockito.`when`(enderecoClient.searchInJson(novoAutorRequest.cep)).thenReturn(HttpResponse.ok(enderecoResponse))

        val request = HttpRequest.POST("/autores",novoAutorRequest)

        val response = client.toBlocking().exchange(request,Any::class.java)

        assertEquals(HttpStatus.CREATED,response.status)
        assertTrue(response.headers.contains("Location"))
        assertTrue(response.header("Location")!!.contains("/autores/"))
    }

    @MockBean(EnderecoClient::class)
    fun enderecoMock():EnderecoClient{
        return Mockito.mock(EnderecoClient::class.java)
    }

}