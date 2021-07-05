package br.com.zupacademy.autores.dto

import br.com.zupacademy.autores.modelo.Autor

class DetalhesAutorResponse(autor: Autor) {

    val nome = autor.nome
    val email = autor.email
    val descricao = autor.descricao
}
