package br.com.zupacademy.autores.controller

import br.com.zupacademy.autores.dto.DetalhesAutorResponse
import br.com.zupacademy.autores.dto.EnderecoResponse
import br.com.zupacademy.autores.feign.EnderecoClient
import br.com.zupacademy.autores.modelo.Autor
import br.com.zupacademy.autores.modelo.Endereco
import br.com.zupacademy.autores.repository.AutorRepository
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class DetalhesAutorControllerTest{

    @field:Inject
    lateinit var autorRepository: AutorRepository

    @field:Inject
    lateinit var enderecoClient: EnderecoClient

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    lateinit var autor: Autor

    @BeforeEach
    internal fun setUp(){
        val enderecoResponse = EnderecoResponse(
            cep = "12912-660",
            logradouro = "Rua Professor Luiz Nardy",
            complemento = "de 235/236 ao fim",
            bairro = "Aparecida",
            localidade = "Bragança Paulista",
            uf = "SP",
            ibge = "3507605",
            gia = "2252",
            ddd = "11",
            siafi = "6251"
        )
        val endereco = Endereco(
            enderecoResponse,
            "43"
        )
        autor = Autor(
            nome = "Vitor",
            email = "vitor@zup.com.br",
            descricao = "tste",
            endereco = endereco
        )
        autorRepository.save(autor)
    }

    @AfterEach
    internal fun tearDown(){
        autorRepository.deleteAll()
    }


    @Test
    internal fun `deve retornar os detalhes de um autor`() {
        val resposta = client.toBlocking().exchange(
            "/autores?email=${autor.email}",
            DetalhesAutorResponse::class.java
        )

        assertEquals(HttpStatus.OK,resposta.status)
        assertNotNull(resposta.body())
        assertEquals(autor.nome, resposta.body()!!.nome)
        assertEquals(autor.email, resposta.body()!!.email)
        assertEquals(autor.descricao, resposta.body()!!.descricao)
    }
}