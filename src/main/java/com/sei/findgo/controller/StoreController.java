package com.sei.findgo.controller;

import com.sei.findgo.models.Store;
import com.sei.findgo.models.StoreSection;
import com.sei.findgo.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api")
public class StoreController {

    private StoreService storeService;

    /**
     * Sets the store service.
     *
     * @param storeService The store service to be set.
     */
    @Autowired
    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }

    /**
     * Adds a new store.
     *
     * @param storeObject The store object to be added.
     * @return The added store.
     */
    @PostMapping(path = "/auth/stores")
    public Store addStore(@RequestBody Store storeObject) {
        return storeService.addStore(storeObject);
    }

    /**
     * Adds a new store section to the specified store.
     *
     * @param storeId The ID of the store.
     * @param storeSectionObject The store section object to be added.
     * @return The added store section.
     */
    @PostMapping(path = "/auth/storeSection/{storeId}")
    public StoreSection addStoreSection(@PathVariable("storeId") int storeId, @RequestBody StoreSection storeSectionObject) {
        return storeService.addStoreSection(storeId, storeSectionObject);
    }

    /**
     * Updates a store section of the specified store.
     *
     * @param storeId The ID of the store.
     * @param storeSectionId The ID of the store section to be updated.
     * @param storeSectionObject The updated store section object.
     * @return The updated store section.
     */
    @PutMapping(path = "/auth/storeSection/{storeId}/{storeSectionId}")
    public StoreSection updateStoreSection(@PathVariable("storeId") int storeId, @PathVariable("storeSectionId") int storeSectionId, @RequestBody StoreSection storeSectionObject) {
        return storeService.updateStoreSection(storeId, storeSectionId, storeSectionObject);
    }

    /**
     * Retrieves all stores.
     *
     * @return A list of all available stores.
     */
    @GetMapping("/stores")
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

    /**
     * Retrieves a store by its ID.
     *
     * @param storeId The ID of the store.
     * @return The store with the specified ID.
     */
    @GetMapping(path = "/stores/storeId/{storeId}")
    public Store getStoreById(@PathVariable("storeId") int storeId) {
        return storeService.findStoreById(storeId);
    }

    /**
     * Retrieves a store by its name.
     *
     * @param storeName The name of the store.
     * @return The store with the specified name.
     */
    @GetMapping(path = "/stores/search/{storeName}")
    public Store getStoreByName(@PathVariable("storeName") String storeName) {
        return storeService.findStoreByName(storeName);
    }

    /**
     * Retrieves a store by its city.
     *
     * @param city The city of the store.
     * @return The store in the specified city.
     */
    @GetMapping(path = "/stores/city/{city}")
    public Store getStoreByCity(@PathVariable("city") String city) {
        return storeService.findStoreByCity(city);
    }
    /**
     * Updates a store with the specified ID.
     *
     * @param storeId The ID of the store to be updated.
     * @param storeObject The updated store object.
     * @return The updated store.
     */
    @PutMapping(path = "auth/stores/{storeId}")
    public Store updateStore(@PathVariable("storeId") int storeId, @RequestBody Store storeObject) {
        return storeService.updateStore(storeId, storeObject);
    }

    /**
     * Deletes a store with the specified ID.
     *
     * @param storeId The ID of the store to be deleted.
     * @return The deleted store.
     */
    @DeleteMapping(path = "auth/stores/{storeId}")
    public Store deleteStore(@PathVariable("storeId") int storeId) {
        return storeService.deleteStore(storeId);
    }
}
