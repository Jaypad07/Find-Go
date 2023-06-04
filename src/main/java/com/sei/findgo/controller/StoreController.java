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

    @Autowired
    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping(path = "/auth/stores")
    public Store addStore(@RequestBody Store storeObject) {
        return storeService.addStore(storeObject);
    }

    @PostMapping(path = "/auth/storeSection/{storeId}")
    public StoreSection addStoreSection(@PathVariable ("storeId") int storeId, @RequestBody StoreSection storeSectionObject) {
        return storeService.addStoreSection(storeId, storeSectionObject);
    }

    @PutMapping(path = "/auth/storeSection/{storeId}/{storeSectionId}")
    public StoreSection updateStoreSection(@PathVariable ("storeId") int storeId, @PathVariable ("storeSectionId") int storeSectionId, @RequestBody StoreSection storeSectionObject) {
        return storeService.updateStoreSection(storeId, storeSectionId, storeSectionObject);
    }

    @GetMapping("/stores")
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

    @GetMapping(path = "/stores/storeId/{storeId}")
    public Store getStore(@PathVariable("storeId") int storeId) {
        return storeService.findStoreById(storeId);
    }

    @GetMapping(path = "/stores/search/{storeName}")
    public Store getStore(@PathVariable("storeName") String storeName) {
        return storeService.findStoreByName(storeName);
    }

    @GetMapping(path = "/stores/city/{city}")
    public Store getStoreByCity(@PathVariable("city") String city) {
        return storeService.findStoreByCity(city);
    }

    @PutMapping(path = "auth/stores/{storeId}")
    public Store updateStore(@PathVariable("storeId") int storeId, @RequestBody Store storeObject) {
        return storeService.updateStore(storeId, storeObject);
    }

    @DeleteMapping(path = "auth/stores/{storeId}")
    public Store deleteStore(@PathVariable("storeId") int storeId) {
        return storeService.deleteStore(storeId);
    }
}
