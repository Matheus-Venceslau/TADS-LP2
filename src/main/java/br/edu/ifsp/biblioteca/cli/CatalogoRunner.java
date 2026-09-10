package br.edu.ifsp.biblioteca.cli;

import br.edu.ifsp.biblioteca.domain.Livro;
import br.edu.ifsp.biblioteca.domain.Usuario;
import br.edu.ifsp.biblioteca.exception.RegraDeNegocioException;
import br.edu.ifsp.biblioteca.repository.LivroRepositoryEmMemoria;
import br.edu.ifsp.biblioteca.repository.UsuarioRepositoryEmMemoria;
import br.edu.ifsp.biblioteca.service.LivroService;
import br.edu.ifsp.biblioteca.service.UsuarioService;

import java.util.List;

public class CatalogoRunner {
    private final LivroService livroService;
    private final UsuarioService usuarioService;

    public CatalogoRunner(){
        this.livroService = new LivroService(new LivroRepositoryEmMemoria());
        this.usuarioService = new UsuarioService(new UsuarioRepositoryEmMemoria());
    }

    public void run(String... args){
        System.out.println();
        System.out.println("====== Biblioteca ");

        Livro domCasmurro = new Livro("9788508145603","Dom Casmurro",1899);

        this.livroService.cadastrar(domCasmurro);
        this.livroService.adicionarAutor(domCasmurro.getId(), "Machado de Assis");
        this.livroService.adicionarExemplar(domCasmurro.getId(), "DC-001");
        this.livroService.adicionarExemplar(domCasmurro.getId(), "DC-002");

        Livro vidasSecas = new Livro("9788508145601","Vidas Secas",1938);

        this.livroService.cadastrar(vidasSecas);
        this.livroService.adicionarAutor(vidasSecas.getId(), "Graciliano Ramos");
        this.livroService.adicionarExemplar(vidasSecas.getId(), "VS-001");

        this.usuarioService.cadastrar(new Usuario("Ana souza", "ana@ifsp.edu.br"));
        this.usuarioService.cadastrar(new Usuario("Bruno Lima", "bruno@ifsp.edu.br"));

        System.out.println();
        System.out.println("--- Catagolo ---");

        for (Livro livro : this.livroService.listarTodos()){
            System.out.println(" " + livro);
        }

        System.out.println();
        System.out.println("--- Usuarios ---");

        for (Usuario usuario : this.usuarioService.listarTodos()){
            System.out.println(" " + usuario);
        }

        System.out.println();
        System.out.println("--- Buscar por titulo contendo 'casmurro' ---");

        List<Livro> encontrados = this.livroService.buscarPorTitulo("Casmurro");


        for (Livro livro : encontrados){
            System.out.println(" " + livro);
        }


        System.out.println();
        System.out.println("--- Testando cadastrar o mesmo ISBN ---");

        try{
            this.livroService.cadastrar(new Livro("9788508145603","Dom Casmurro",1899));
        }catch (RegraDeNegocioException erro){
            System.out.println(" Regra de negocio impediu: " + erro.getMessage());
        }
    }

}


