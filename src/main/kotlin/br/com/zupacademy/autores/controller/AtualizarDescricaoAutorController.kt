package br.com.zupacademy.autores.controller

import br.com.zupacademy.autores.repository.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put

@Controller("/autores")
class AtualizarDescricaoAutorController (val autorRepository: AutorRepository){

    @Put("/{id}")
    fun atualizar(@PathVariable id: Long, descricao: String) : HttpResponse<Any>{

        val possivelAutor = autorRepository.findById(id)
        if(possivelAutor.isEmpty){
            return HttpResponse.notFound()
        }
        possivelAutor.get().descricao = descricao
        autorRepository.update(possivelAutor.get())

        return HttpResponse.ok(possivelAutor.get())
    }
}