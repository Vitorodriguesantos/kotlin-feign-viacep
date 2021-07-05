package br.com.zupacademy.autores.repository

import br.com.zupacademy.autores.modelo.Autor
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface AutorRepository : JpaRepository<Autor,Long> {

    //poderia ser substituido por @Query (SELECT a FROM Autor a WHERE a.email = :email)
    fun findByEmail(email: String): Optional<Autor>
}