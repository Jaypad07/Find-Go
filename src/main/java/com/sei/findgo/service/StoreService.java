package com.sei.findgo.service;

import com.sei.findgo.exceptions.InformationNotFoundException;
import com.sei.findgo.exceptions.NoAuthorizationException;
import com.sei.findgo.exceptions.ProductNotFoundException;
import com.sei.findgo.models.Store;
import com.sei.findgo.models.User;
import com.sei.findgo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    private StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }


    public Store addStore(Store storeObject) {
        Optional<User> user = Optional.ofNullable(UserService.getCurrentLoggedInUser());
        if (user.isPresent() && user.get().getRole().equals("Admin")) {
            return storeRepository.save(storeObject);
        } else throw new InformationNotFoundException("You are not authorized to perform this action");
    }

    public List<Store> getAllStores() {
        List<Store> storeList = storeRepository.findAll();
        if (storeList.size() == 0) {
            throw new InformationNotFoundException("No stores found");
        } else return storeList;
    }

    public Store findStoreById(int id) {
        Optional<Store> store = storeRepository.findById(id);
        if (store.isPresent()) {
            return store.get();
        } else throw new InformationNotFoundException("The store you are looking for does not exist");
    }

    public Store findStoreByName(String name) {
        Optional<Store> store = storeRepository.findByStoreName(name);
        if (store.isPresent()) {
            return store.get();
        } else throw new InformationNotFoundException("The store you are looking for does not exist");
    }

    public Store findStoreByLocation(String location) {
        Optional<Store> store = storeRepository.findStoreByLocation(location);
        if (store.isPresent()) {
            return store.get();
        } else throw new InformationNotFoundException("The store you are looking for does not exist");
    }

    public Store updateStore(int storeId, Store storeObject) {
        Optional<User> user = Optional.ofNullable(UserService.getCurrentLoggedInUser());
        if (user.isPresent() && user.get().getRole().equals("Admin")) {
            Optional<Store> store = storeRepository.findById(storeId);
            if (store.isPresent()) {
                Store existingStore = store.get();
                existingStore.setStoreName(storeObject.getStoreName());
                existingStore.setLocation(storeObject.getLocation());
                existingStore.setMap(storeObject.getMap());
                storeRepository.save(existingStore);
                return existingStore;
            } else throw new InformationNotFoundException("The store you are looking for does not exist");
        } else throw new InformationNotFoundException("You are not authorized to perform this action");
    }

    public Store deleteStore(int storeId) {
        Optional<User> user = Optional.ofNullable(UserService.getCurrentLoggedInUser());
        if (user.isPresent() && user.get().getRole().equals("Admin")) {
            Optional<Store> store = storeRepository.findById(storeId);
            if (store.isPresent()) {
                storeRepository.deleteById(storeId);
                return store.get();
            } else throw new ProductNotFoundException("Store with id " + storeId + " not found.");
        } else throw new NoAuthorizationException("User not authorized to delete product.");
    }
}
