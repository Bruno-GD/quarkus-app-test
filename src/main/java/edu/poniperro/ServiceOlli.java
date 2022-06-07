package edu.poniperro;

import edu.poniperro.dominio.Item;
import edu.poniperro.dominio.Orden;
import edu.poniperro.dominio.Usuaria;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
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

    /**
     * Devuelve una lista con los pedidos de la usuaria
     * con el nombre indicado, si existe.
     * Si no existe, devuelve una lista vacía.
     *
     * @param nombreUsuaria nombre de la Usuaria
     * @return Lista de ordenes
     * @see Orden
     * @see Item
     * @see Usuaria
     */
    public List<Orden> cargaOrden(String nombreUsuaria) {
        return Orden.getByUsuariaName(nombreUsuaria);
    }

    /**
     * Devuelve una nueva orden para la {@link Usuaria} y
     * el {@link Item} indicado, si ambos existen.
     * Además, guarda la Orden en base de datos.
     *
     * Si la {@link Usuaria#getDestreza()} < {@link Item#getQuality()} no creará
     * ninguna orden.
     *
     * @param nombreUsuaria el nombre de la Usuaria
     * @param nombreItem el nombre del Item
     * @return nueva Orden o null
     * @see Orden
     * @see Usuaria
     * @see Item
     */
    @Transactional
    public Orden comanda(String nombreUsuaria, String nombreItem) {
        // obtenemos la Usuaria y el Item de base de datos
        Optional<Usuaria> usuaria = Usuaria.findByIdOptional(nombreUsuaria);
        Optional<Item> item = Item.findByIdOptional(nombreItem);

        if (usuaria.isEmpty() || item.isEmpty()) return null;
        if (usuaria.get().getDestreza() < item.get().getQuality()) return null;

        // Creamos una nueva Orden para Usuaria con Item
        Orden orden = new Orden(usuaria.get(), item.get());
        // La guardamos en base de datos
        orden.persist();
        // Devolvemos la entidad persistente
        return orden;
    }

    /**
     * Devuelve una lista de órdenes para la {@link Usuaria} con los {@link Item Items} indicados.
     * Todas las órdenes se guardarán en base de datos,
     * siguiendo las condiciones de {@link #comanda(String, String) Comanda}.
     *
     * @param nombreUsuaria el nombre de la Usuaria
     * @param nombresItem los nombres de los Items
     * @return lista de nuevas Ordenes
     * @see #comanda(String, String)
     */
    @Transactional
    // es importante el uso de Transactional, ya que en este método haremos inserts múltiples
    public List<Orden> comandaMultiple(String nombreUsuaria, List<String> nombresItem) {
        // obtenemos la Usuaria
        Optional<Usuaria> usuaria = Usuaria.findByIdOptional(nombreUsuaria);

        if (usuaria.isEmpty()) return null;

        List<Orden> ordenes = new ArrayList<>();
        // creamos las órdenes solicitadas
        for (String nombreItem :
                nombresItem) {
            // obtenemos la orden
            Optional<Orden> orden = Optional.ofNullable(this.comanda(usuaria.get().getNombre(), nombreItem));
            // la guardamos si es posible
            orden.ifPresent(ordenes::add);
        }

        return ordenes;
    }
}
