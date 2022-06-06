package edu.poniperro.dominio;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_items")
public class Item extends PanacheEntityBase { // IMPORTANTE usar PanacheEntityBase si no queremos usar identificadores!

    @Id
    @Column(name = "item_nom")
    private String nombre = ""; // si la usuaria no existe, este será su valor por defecto

    @Column(name = "item_prop")
    private int quality = 0; // si la usuaria no existe, este será su valor por defecto

    @Column(name = "item_tipo")
    private String tipo = ""; // si la usuaria no existe, este será su valor por defecto

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
