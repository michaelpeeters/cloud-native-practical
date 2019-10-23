package com.ezgroceries.shoppinglist.list.persistence;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingListRepository extends CrudRepository<ShoppingListEntity, UUID> {

}
