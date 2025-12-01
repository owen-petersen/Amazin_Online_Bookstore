package net.azurewebsites.amazin_online_bookstore.service;

import net.azurewebsites.amazin_online_bookstore.entity.Purchase;
import net.azurewebsites.amazin_online_bookstore.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The PurchaseService class handles typical operations for handling purchase history information.
 *
 * @author Marvel Adotse-ogah
 * @version 2025-11-17
 */
@Service
public class PurchaseService {
    PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public void saveNewPurchase(Purchase newPurchase) {
        this.purchaseRepository.save(newPurchase);
    }
}
