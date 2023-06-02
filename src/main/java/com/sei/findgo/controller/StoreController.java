package com.sei.findgo.controller;

import com.sei.findgo.models.Store;
import com.sei.findgo.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/stores/{storeId}")
    public Store getStore(@PathVariable("storeId") int storeId) {
        return storeService.findStoreById(storeId);
    }


}
