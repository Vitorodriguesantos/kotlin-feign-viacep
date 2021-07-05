package br.com.zupacademy.autores.controller

import br.com.zupacademy.autores.repository.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable

@Controller("/autores")
class DeletaAutorController (val autorRepository: AutorRepository){

    @Delete("/{id}")
    fun deletar(@PathVariable id: Long) : HttpResponse<Any>{

        val possivelAutor = autorRepository.findById(id)
        if(possivelAutor.isEmpty) {
          return HttpResponse.notFound()
        }
        autorRepository.deleteById(id)
       return HttpResponse.ok()
    }
}