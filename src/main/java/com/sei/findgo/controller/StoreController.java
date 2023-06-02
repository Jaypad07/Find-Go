package com.sei.findgo.controller;

import com.sei.findgo.models.Store;
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

    @GetMapping("/stores")
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

    @GetMapping(path = "/stores/{storeId}")
    public Store getStore(@PathVariable("storeId") int storeId) {
        return storeService.findStoreById(storeId);
    }

    @GetMapping(path = "/stores/{storeName}")
    public Store getStore(@PathVariable("storeName") String storeName) {
        return storeService.findStoreByName(storeName);
    }

    @GetMapping(path = "/stores/{location}")
    public Store getStoreByLocation(@PathVariable("location") String location) {
        return storeService.findStoreByLocation(location);
    }

    @PutMapping(path = "auth/stores/{storeId}")
    public Store updateStore(@PathVariable("storeId") int storeId, @RequestBody Store storeObject) {
        return storeService.updateStore(storeId, storeObject);
    }

    @DeleteMapping(path = "auth/stores/{storeId}")
    public String deleteStore(@PathVariable("storeId") int storeId) {
        return storeService.deleteStore(storeId);
    }
}
