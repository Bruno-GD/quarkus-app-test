package edu.poniperro.dominio;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_users")
public class Usuaria extends PanacheEntityBase { // IMPORTANTE usar PanacheEntityBase si no se va a usar identificadores!

    @Id
    @Column(name = "user_nom")
    private String nombre = ""; // si la usuaria no existe, este será su valor por defecto

    @Column(name = "user_prop")
    private int destreza = 0; // si la usuaria no existe, este será su valor por defecto

    public Usuaria() {}

    public Usuaria(String nombreUsuaria) {
        this.nombre = nombreUsuaria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }
}
