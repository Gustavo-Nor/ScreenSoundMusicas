package com.ScreenSoundMusicas.ScreenSoundMusicas.model;

public enum Categoria {
    SOLO("solo"),
    DUPLA("dupla"),
    BANDA("banda");

    private String tipoArtista;

    Categoria(String tipoArtista) {
        this.tipoArtista = tipoArtista;
    }

    public static Categoria fromString(String text){
        for (Categoria categoria : Categoria.values()){
            if(categoria.tipoArtista.equalsIgnoreCase(text)){
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
