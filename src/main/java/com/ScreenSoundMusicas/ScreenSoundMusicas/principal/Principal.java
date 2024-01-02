package com.ScreenSoundMusicas.ScreenSoundMusicas.principal;

import com.ScreenSoundMusicas.ScreenSoundMusicas.model.Artista;
import com.ScreenSoundMusicas.ScreenSoundMusicas.model.Categoria;
import com.ScreenSoundMusicas.ScreenSoundMusicas.model.Musica;
import com.ScreenSoundMusicas.ScreenSoundMusicas.repository.ArtistaRepository;
import com.ScreenSoundMusicas.ScreenSoundMusicas.service.ConsultaChatGPT;

import java.util.*;

public class Principal {

    private Scanner leitura = new Scanner(System.in);

    private ArtistaRepository repositorio;
    public Principal(ArtistaRepository repositorio) {
        this.repositorio = repositorio;
    }


    private List<Musica> musicas = new ArrayList<>();


    public void exibirMenu() {
        var opcao = -1;

        while(opcao != 0){
            var menu = """
                    1 - Cadastrar artistas
                    2 - Cadastrar músicas
                    3 - Listar músicas
                    4 - Buscar músicas por artista
                    5 - Pesquisar dados sobre um artista
                    
                    0 - Sair 
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao){
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusica();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicasPorArtista();
                    break;
                case 5:
                    pesquisarSobreUmArtista();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void cadastrarArtista() {
        System.out.println("Digite o nome do artista que voce vai cadastrar: ");
        var nomeArtista = leitura.nextLine();
        System.out.println("Qual o tipo do artista? (solo, dupla, banda)");
        var tipoArtista = leitura.nextLine();
        Categoria categoria = Categoria.fromString(tipoArtista);
        Artista artista = new Artista(nomeArtista, categoria);
        repositorio.save(artista);
    }

    private void cadastrarMusica() {
        List<Artista> listaArtistas = repositorio.findAll();
        listaArtistas.forEach(a -> System.out.println("ID: " + a.getId() + " Nome do artista: " + a.getNome()));
        System.out.println("De qual artista acima é a música que voce quer cadastrar? (digite o ID) ");
        var artistaId = leitura.nextLong();
        leitura.nextLine();

        Optional<Artista> artistaBuscado = repositorio.findById(artistaId);
        if(artistaBuscado.isPresent()){
            Artista artista = artistaBuscado.get();
            System.out.println("Digite a música do artista " + artista.getNome() + " que voce deseja cadastrar: ");
            var musicaCadastrar = leitura.nextLine();
            Musica musicaAdicionar = new Musica(musicaCadastrar,artista);
            List<Musica> musicas = new ArrayList<>();
            musicas.add(musicaAdicionar);
            artista.setMusicas(musicas);
            repositorio.save(artista);
        } else {
            System.out.println("Artista não encontrado!");
        }
    }

    private void listarMusicas() {
        List<Musica> listaMusicas = repositorio.retornarListaDeMusicas();
        listaMusicas.forEach(m -> System.out.println("Artista: " + m.getArtista().getNome() + " ||| Música: " + m.getNome()));
    }

    private void buscarMusicasPorArtista() {
        List<Artista> listaArtistas = repositorio.findAll();
        listaArtistas.forEach(a -> System.out.println("ID: " + a.getId() + " Nome do artista: " + a.getNome()));
        System.out.println("Voce deseja listar as músicas de qual artista? (digite o ID do artista)");
        var artistaId = leitura.nextLong();
        leitura.nextLine();

        Optional<Artista> artistaBuscado = repositorio.findById(artistaId);
        if(artistaBuscado.isPresent()){
            Artista artista = artistaBuscado.get();
            List<Musica> musicasPorArtista = repositorio.musicasPorArtista(artista.getId());
            musicasPorArtista.forEach(m -> System.out.println(m.getNome()));
        } else {
            System.out.println("Artista não encontrado!");
        }
    }

    private void pesquisarSobreUmArtista() {
        System.out.println("Sobre qual artista voce deseja pesquisar?");
        var artista = leitura.nextLine();
        System.out.println(ConsultaChatGPT.pesquisarSobre(artista).trim());
    }
}
