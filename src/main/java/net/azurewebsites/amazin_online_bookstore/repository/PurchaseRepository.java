package net.azurewebsites.amazin_online_bookstore.repository;

import net.azurewebsites.amazin_online_bookstore.entity.Purchase;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PurchaseRepository extends CrudRepository<Purchase,Integer> {
    Optional<Purchase> findById(Integer id);
}
