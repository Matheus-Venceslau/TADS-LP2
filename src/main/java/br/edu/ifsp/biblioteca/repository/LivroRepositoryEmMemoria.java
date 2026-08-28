package br.edu.ifsp.biblioteca.repository;

import br.edu.ifsp.biblioteca.domain.Livro;

import java.lang.reflect.Array;
import java.util.*;

public class LivroRepositoryEmMemoria implements ILivroRepository{
    private final Map<Long, Livro> livos = new HashMap<>();
    private Long sequenciaId = 0L;

    @Override
    public Livro salvar(Livro livro) {
        if (livro.getId() == null) {
            this.sequenciaId = this.sequenciaId + 1; // this.sequenciaId++;
            livro.setId(this.sequenciaId);
        }

        this.livos.put(livro.getId(), livro);
        return livro;
    }

    @Override
    public List<Livro> listarTodos() {
        return new ArrayList<>(livos.values());
    }

    @Override
    public Optional<Livro> buscarPorId(Long id) {
        Livro l = this.livos.get(id);

//        return Optional.ofNullable(this.livos.get(id));

        return l == null ? Optional.empty() : Optional.of(l);

//        if(l == null){
//            return Optional.empty();
//        }
//
//        return Optional.of(l);
    }

    @Override
    public Optional<Livro> buscarPorIsbn(String isbn) {
        ArrayList<Livro> colecaoLivros = new ArrayList<>(this.livos.values());

        for (int i = 0; i < colecaoLivros.size(); i++) {
            if (colecaoLivros.get(i).getIsbn().equals(isbn)){
                return Optional.of(colecaoLivros.get(i));
            }
        }
        
        return Optional.empty();
    }

    @Override
    public List<Livro> buscarPorTitulo(String titulo) {
        ArrayList<Livro> colecaoLivros = new ArrayList<>(this.livos.values());
        List<Livro> livrosEncontrados = new ArrayList<>();

        for (Livro livroAtual : colecaoLivros){

            if (livroAtual.getTitulo().contains(titulo)){
                livrosEncontrados.add(livroAtual);
            }
        }
        return livrosEncontrados;
    }
}
