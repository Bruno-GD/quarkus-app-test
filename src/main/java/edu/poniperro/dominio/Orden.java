package edu.poniperro.dominio;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import javax.persistence.*;
import java.util.List;

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

    public Orden() {}

    public Orden(Usuaria usuaria, Item item) {
        this.setUser(usuaria);
        this.setItem(item);
    }

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

    public static List<Orden> getByUsuariaName(String name) {
        return list("ord_user", name);
    }
}
