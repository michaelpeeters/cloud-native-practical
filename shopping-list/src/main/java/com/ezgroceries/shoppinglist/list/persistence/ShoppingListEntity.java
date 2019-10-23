package com.ezgroceries.shoppinglist.list.persistence;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SHOPPING_LIST")
public class ShoppingListEntity {

    @Id
    @Column(name = "ID")
 //   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID uuid;

    @Column(name = "NAME")
    private String name;

    public ShoppingListEntity() {
        this.uuid = UUID.randomUUID();

    }

    public ShoppingListEntity(String name) {
        this.uuid = UUID.randomUUID();
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
