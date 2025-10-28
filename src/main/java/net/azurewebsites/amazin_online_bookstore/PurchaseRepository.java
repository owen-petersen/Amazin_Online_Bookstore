package net.azurewebsites.amazin_online_bookstore;

import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<Purchase,Integer> {
}
