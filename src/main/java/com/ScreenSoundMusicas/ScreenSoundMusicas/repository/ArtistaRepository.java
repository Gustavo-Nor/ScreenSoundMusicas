package com.ScreenSoundMusicas.ScreenSoundMusicas.repository;

import com.ScreenSoundMusicas.ScreenSoundMusicas.model.Artista;
import com.ScreenSoundMusicas.ScreenSoundMusicas.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    @Query("SELECT m FROM Artista a JOIN a.musicas m")
    List<Musica> retornarListaDeMusicas();

    @Query("SELECT m FROM Artista a JOIN a.musicas m WHERE a.id = :id")
    List<Musica> musicasPorArtista(Long id);
}
