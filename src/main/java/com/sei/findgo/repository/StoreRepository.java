package com.sei.findgo.repository;

import com.sei.findgo.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    Optional<Store> findByStoreNameIgnoreCase(String storeName);

    Optional<Store> findStoreByAddressContainsIgnoreCase(String city);
}
