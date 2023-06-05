package com.sei.findgo.service;

import com.sei.findgo.exceptions.InformationExistException;
import com.sei.findgo.exceptions.InformationNotFoundException;
import com.sei.findgo.exceptions.NoAuthorizationException;
import com.sei.findgo.exceptions.ProductNotFoundException;
import com.sei.findgo.models.Store;
import com.sei.findgo.models.StoreSection;
import com.sei.findgo.models.User;
import com.sei.findgo.repository.StoreRepository;
import com.sei.findgo.repository.StoreSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing stores and store sections.
 *
 * This class provides methods to perform operations on stores and store sections.
 * It interacts with the {@link StoreRepository} to access and manipulate store data.
 * The service methods implement various business logic and authorization checks.
 */

@Service
public class StoreService {

    private StoreRepository storeRepository;

    private StoreSectionRepository storeSectionRepository;

    @Autowired
    public void setStoreRepository(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Autowired
    public void setStoreSectionRepository(StoreSectionRepository storeSectionRepository) {
        this.storeSectionRepository = storeSectionRepository;
    }

    /**
     * Adds a new store.
     *
     * @param storeObject The store object to be added.
     * @return The added store.
     * @throws NoAuthorizationException If the user is not authorized to add the store.
     */
    public Store addStore(Store storeObject) {
        Optional<User> user = Optional.ofNullable(UserService.getCurrentLoggedInUser());
        if (user.isPresent() && user.get().getRole().equals("Admin")) {
            return storeRepository.save(storeObject);
        } else {
            throw new NoAuthorizationException("You are not authorized to perform this action");
        }
    }

    /**
     * Retrieves all store sections.
     *
     * This method retrieves all store sections from the database.
     *
     * @return a list of store sections
     * @throws InformationNotFoundException if no store sections exist
     * @throws NoAuthorizationException if the user is not authorized to perform this action
     */
    public List<StoreSection> getAllStoreSections() {
        Optional<User> user = Optional.ofNullable(UserService.getCurrentLoggedInUser());
        if (user.isPresent() && user.get().getRole().equals("Admin") || user.isPresent() && user.get().getRole().equals("Manager")) {
            List<StoreSection> storeSectionList = storeSectionRepository.findAll();
            if (storeSectionList.size() > 0) {
                return storeSectionList;
            } else throw new InformationNotFoundException("No store sections exist");
        }else throw new NoAuthorizationException("You are not authorized to perform this action");
    }

    /**
     * Adds a new store section to a store.
     *
     * @param storeId The ID of the store to add the section to.
     * @param storeSectionObject The store section object to be added.
     * @return The added store section.
     * @throws InformationNotFoundException If the store with the specified ID does not exist.
     * @throws NoAuthorizationException If the user is not authorized to add the store section.
     * @throws InformationExistException If a store section with the same name already exists.
     */
    public StoreSection addStoreSection(int storeId, StoreSection storeSectionObject) {
        Optional<User> user = Optional.ofNullable(UserService.getCurrentLoggedInUser());
        if (user.isPresent() && user.get().getRole().equals("Admin") || user.isPresent() && user.get().getRole().equals("Manager")) {
            Optional<Store> store = storeRepository.findById(storeId);
            if (store.isPresent()) {
               Optional<StoreSection> storeSection = Optional.ofNullable(storeSectionRepository.findStoreSectionBySectionNameIgnoreCase(storeSectionObject.getSectionName()));
               if (storeSection.isPresent()) {
                   throw new InformationExistException("A store section with the same name already exists");
               } else {
                   store.get().getStoreSectionsList().add(storeSectionObject);
                   storeSectionObject.setStore(store.get());
                   storeSectionRepository.save(storeSectionObject);
                   return storeSectionObject;
               }
            } else {
                throw new InformationNotFoundException("The store you are looking for does not exist");
            }
        } else {
            throw new NoAuthorizationException("You are not authorized to perform this action");
        }
    }

    /**
     * Updates a store section.
     *
     * @param storeId The ID of the store that contains the section.
     * @param storeSectionId The ID of the store section to update.
     * @param storeSectionObject The updated store section object.
     * @return The updated store section.
     * @throws InformationNotFoundException If the store with the specified ID does not exist.
     * @throws NoAuthorizationException If the user is not authorized to update the store section.
     */
    public StoreSection updateStoreSection(int storeId, int storeSectionId, StoreSection storeSectionObject) {
        Optional<User> user = Optional.ofNullable(UserService.getCurrentLoggedInUser());
        if (user.isPresent() && user.get().getRole().equals("Admin") || user.isPresent() && user.get().getRole().equals("Manager")) {
            Optional<Store> store = storeRepository.findById(storeId);
            if (store.isPresent()) {
                Store existingStore = store.get();
                StoreSection updatedSection = existingStore.getStoreSectionsList().get(storeSectionId);
                updatedSection.setSectionName(storeSectionObject.getSectionName());
                updatedSection.setProductList(storeSectionObject.getProductList());
//                storeRepository.save(existingStore);
                storeSectionRepository.save(storeSectionObject);
                return updatedSection;
            } else {
                throw new InformationNotFoundException("The store you are looking for does not exist");
            }
        } else {
            throw new NoAuthorizationException("You are not authorized to perform this action");
        }
    }

    /**
     * Retrieves all stores.
     *
     * @return A list of all stores.
     * @throws InformationNotFoundException If no stores are found.
     */
    public List<Store> getAllStores() {
        List<Store> storeList = storeRepository.findAll();
        if (storeList.size() == 0) {
            throw new InformationNotFoundException("No stores found");
        } else {
            return storeList;
        }
    }

    /**
     * Retrieves a store by its ID.
     *
     * @param id The ID of the store to retrieve.
     * @return The store with the specified ID.
     * @throws InformationNotFoundException If the store with the specified ID does not exist.
     */
    public Store findStoreById(int id) {
        Optional<Store> store = storeRepository.findById(id);
        if (store.isPresent()) {
            return store.get();
        } else {
            throw new InformationNotFoundException("The store you are looking for does not exist");
        }
    }

    /**
     * Retrieves a store by its name.
     *
     * @param name The name of the store to retrieve.
     * @return The store with the specified name.
     * @throws InformationNotFoundException If the store with the specified name does not exist.
     */
    public Store findStoreByName(String name) {
        Optional<Store> store = storeRepository.findByStoreNameIgnoreCase(name);
        if (store.isPresent()) {
            return store.get();
        } else {
            throw new InformationNotFoundException("The store you are looking for does not exist");
        }
    }

    /**
     * Retrieves a store by its city.
     *
     * @param city The city of the store to retrieve.
     * @return The store with the specified city.
     * @throws InformationNotFoundException If the store with the specified city does not exist.
     */
    public Store findStoreByCity(String city) {
        Optional<Store> store = storeRepository.findStoreByCityIgnoreCase(city);
        if (store.isPresent()) {
            return store.get();
        } else {
            throw new InformationNotFoundException("The store you are looking for does not exist");
        }
    }

    /**
     * Updates a store.
     *
     * @param storeId The ID of the store to update.
     * @param storeObject The updated store object.
     * @return The updated store.
     * @throws InformationNotFoundException If the store with the specified ID does not exist.
     * @throws NoAuthorizationException     If the user is not authorized to update the store.
     */
    public Store updateStore(int storeId, Store storeObject) {
        Optional<User> user = Optional.ofNullable(UserService.getCurrentLoggedInUser());
        if (user.isPresent() && user.get().getRole().equals("Admin")) {
            Optional<Store> store = storeRepository.findById(storeId);
            if (store.isPresent()) {
                Store existingStore = store.get();
                existingStore.setStoreName(storeObject.getStoreName());
                existingStore.setCity(storeObject.getCity());
                existingStore.setMap(storeObject.getMap());
                storeRepository.save(existingStore);
                return existingStore;
            } else {
                throw new InformationNotFoundException("The store you are looking for does not exist");
            }
        } else {
            throw new NoAuthorizationException("You are not authorized to perform this action");
        }
    }

    /**
     * Deletes a store.
     *
     * @param storeId The ID of the store to delete.
     * @return The deleted store.
     * @throws ProductNotFoundException  If the store with the specified ID does not exist.
     * @throws NoAuthorizationException If the user is not authorized to delete the store.
     */
    public Store deleteStore(int storeId) {
        Optional<User> user = Optional.ofNullable(UserService.getCurrentLoggedInUser());
        if (user.isPresent() && user.get().getRole().equals("Admin")) {
            Optional<Store> store = storeRepository.findById(storeId);
            if (store.isPresent()) {
                storeRepository.deleteById(storeId);
                return store.get();
            } else {
                throw new ProductNotFoundException("Store with id " + storeId + " not found.");
            }
        } else {
            throw new NoAuthorizationException("User not authorized to delete product.");
        }
    }
}