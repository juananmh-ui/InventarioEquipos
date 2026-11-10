package cesur.dam.inventarioappg1.models;

import lombok.Data;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Data
public class Equipo {
    public static final List<String> CATEGORIAS = List.of(
            "Impresora", "NAS", "PC Sobremesa", "Portátil", "Proyector",
            "Router", "Switch", "Tablet", "Videojuego", "Webcam"
    );

    private static final Map<String, String> CATEGORIAS_ANTERIORES = Map.of(
            "computer", "PC Sobremesa",
            "gamepad", "Mando",
            "keyboard", "Teclado",
            "laptop", "Portátil",
            "monitor", "Monitor",
            "printer", "Impresora",
            "videogame", "Videojuego"
    );

    private Long id;
    private String nombre;
    private String categoria;
    private Boolean disponible;

    public String getCategoriaVisible() {
        if (categoria == null) {
            return "Sin categoría";
        }

        String valor = categoria.strip();
        return CATEGORIAS.stream()
                .filter(nombreCategoria -> nombreCategoria.equalsIgnoreCase(valor))
                .findFirst()
                .orElseGet(() -> CATEGORIAS_ANTERIORES.getOrDefault(
                        valor.toLowerCase(Locale.ROOT), "Sin categoría"));
    }
}
