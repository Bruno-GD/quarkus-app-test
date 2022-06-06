package edu.poniperro;

import edu.poniperro.dominio.Item;
import edu.poniperro.dominio.Usuaria;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class ServiceOlli {

    /**
     * Devuelve la usuaria con el nombre indicado, si existe.
     * Si no existe, devuelve un objeto {@link edu.poniperro.dominio.Usuaria} con sus propiedades
     * y valores como se indica en los casos test.
     *
     * @param name nombre de la Usuaria
     * @return Usuaria
     * @see Usuaria
     */
    public Usuaria cargaUsuaria(String name) {
        // Haciendo uso de PanacheEntityBase, buscamos por "Id" la Usuaria
        Optional<Usuaria> usuaria = Usuaria.findByIdOptional(name);

        return usuaria.isPresent()
                ? usuaria.get()
                : new Usuaria();
    }

    /**
     * Devuelve el item con el nombre indicado, si existe.
     * Si no existe, devuelve un objeto {@link edu.poniperro.dominio.Item} con sus propiedades
     * y valores como se indica en los casos test.
     *
     * @param itemName nombre del item
     * @return Item
     * @see Item
     */
    public Item cargaItem(String itemName) {
        // Haciendo uso de PanacheEntityBase, buscamos por "Id" el Item
        Optional<Item> item = Item.findByIdOptional(itemName);

        return item.isPresent()
                ? item.get()
                : new Item();
    }
}
