package cesur.dam.inventarioappg1.models;

import lombok.Data;

@Data
public class Equipo {
    private Long id;
    private String nombre;
    private String categoria;
    private Boolean disponible;
}
