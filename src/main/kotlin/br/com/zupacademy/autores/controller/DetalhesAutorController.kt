package br.com.zupacademy.autores.controller

import br.com.zupacademy.autores.repository.AutorRepository
import br.com.zupacademy.autores.dto.DetalhesAutorResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue

@Controller("/autores")
class DetalhesAutorController(val autorRepository: AutorRepository) {

    @Get
    fun buscar(@QueryValue (defaultValue = "")email: String): HttpResponse<Any> {

        if(email.isBlank()){
            val autores = autorRepository.findAll()
            val resposta = autores.map(::DetalhesAutorResponse)
            return HttpResponse.ok(resposta)
        }

        val possivelAutor = autorRepository.findByEmail(email)
        if(possivelAutor.isEmpty){
            return HttpResponse.notFound()
        }
        return HttpResponse.ok(DetalhesAutorResponse(possivelAutor.get()))
    }
}