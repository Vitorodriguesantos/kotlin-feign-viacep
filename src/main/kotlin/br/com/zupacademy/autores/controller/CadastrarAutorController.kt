package br.com.zupacademy.autores.controller

import br.com.zupacademy.autores.repository.AutorRepository
import br.com.zupacademy.autores.dto.NovoAutorRequest
import br.com.zupacademy.autores.feign.EnderecoClient
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller(value = "/autores")
class CadastrarAutorController(
    val autorRepository: AutorRepository,
    val enderecoClient: EnderecoClient,
) {

    @Post
    fun cadastrar(@Body @Valid request: NovoAutorRequest): HttpResponse<Any>{
        val oEndereco = enderecoClient.searchInJson(request.cep)
        val novoAutor = request.paraAutor(oEndereco.body())
        autorRepository.save(novoAutor)
        val uri = UriBuilder.of("/autores/{id}")
            .expand(mutableMapOf(Pair("id", novoAutor.id)))
        return HttpResponse.created(uri)
    }
}
