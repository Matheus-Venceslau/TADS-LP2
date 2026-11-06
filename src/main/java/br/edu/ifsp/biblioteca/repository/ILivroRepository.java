package br.edu.ifsp.biblioteca.repository;

import br.edu.ifsp.biblioteca.domain.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/*
*
* JpaRepositoru fornece os seguintes métodos (dentre outros):
* - save, findById, findAll, count
*
* */
public interface ILivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

    List<Livro> findByTituloContainingIgnoreCase(String trechoTitulo);

    Page<Livro> findByTituloContainingIgnoreCase(String trechoTitulo, Pageable pagina);

    List<Livro> buscarPorTitulo(String titulo);
}
