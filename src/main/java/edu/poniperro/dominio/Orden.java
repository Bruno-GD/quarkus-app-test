package edu.poniperro.dominio;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.security.IdentityAttribute;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;

import edu.poniperro.dominio.Usuaria;
import edu.poniperro.dominio.Item;

@Entity
@Table(name = "t_ordenes")
public class Orden extends PanacheEntityBase { // IMPORTANTE usar PanacheEntityBase si no vas a usar identificadores!

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ord_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "ord_user")
    private Usuaria user;

    @OneToOne
    @JoinColumn(name = "ord_item")
    private Item item;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuaria getUser() {
        return user;
    }

    public void setUser(Usuaria user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
