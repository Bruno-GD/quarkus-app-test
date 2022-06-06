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
    private String nombre;

    @Column(name = "item_prop")
    private int quality;

    @Column(name = "item_tipo")
    private String tipo;

    public String getNombre() {
        return nombre;
    }

    public int getQuality() {
        return quality;
    }

    public String getTipo() {
        return tipo;
    }
}
