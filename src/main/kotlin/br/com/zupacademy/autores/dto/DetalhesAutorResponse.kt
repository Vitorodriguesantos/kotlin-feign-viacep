package br.com.zupacademy.autores.dto

import br.com.zupacademy.autores.modelo.Autor

class DetalhesAutorResponse(
    val nome: String,
    val email: String,
    val descricao: String,
) {

    constructor(autor: Autor): this(
        nome = autor.nome,
        email = autor.email,
        descricao = autor.descricao
    )
}
