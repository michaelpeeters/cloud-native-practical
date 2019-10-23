package com.ezgroceries.shoppinglist.cocktail.persistence;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cocktail")
public class CocktailEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID uuid;

    @Column(name = "ID_DRINK")
    private String idDrink;

    @Column(name = "NAME")
    private String name;

    @Column(name = "INGREDIENTS")
    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;

    public CocktailEntity() {
        this.uuid = UUID.randomUUID();
    }

    public CocktailEntity(String idDrink, String name, Set<String> ingredients) {
        this.uuid = UUID.randomUUID();
        this.idDrink = idDrink;
        this.name = name;
        this.ingredients = ingredients;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(String idDrink) {
        this.idDrink = idDrink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CocktailEntity that = (CocktailEntity) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(idDrink, that.idDrink) && Objects.equals(name, that.name) && Objects
                .equals(ingredients, that.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, idDrink, name, ingredients);
    }
}



