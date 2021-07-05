package br.com.zupacademy.autores.modelo

import br.com.zupacademy.autores.dto.EnderecoResponse
import javax.persistence.Embeddable

@Embeddable
class Endereco (enderecoResponse: EnderecoResponse, val numero:String){

    val rua = enderecoResponse.logradouro
    val bairro = enderecoResponse.bairro
    val cidade = enderecoResponse.localidade
    val estado = enderecoResponse.uf
}
