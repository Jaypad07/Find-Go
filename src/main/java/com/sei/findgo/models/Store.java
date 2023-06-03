package com.sei.findgo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stores")
public class Store {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String storeName;

    @Column
    private String description;

    @Column
    private String location;

    @Column
    private String map;

    @JsonIgnore
    @ManyToMany(mappedBy = "storeList")
    private List<User> userList;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore //Can remove later? Caused StackOverflow
    private List<StoreSection> storeSectionsList;

    public Store() {
    }

    public Store(String storeName, String location, String description, String map) {
        this.storeName = storeName;
        this.description = description;
        this.location = location;
        this.map = map;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<StoreSection> getStoreSectionsList() {
        return storeSectionsList;
    }

    public void setStoreSectionsList(List<StoreSection> storeSectionsList) {
        this.storeSectionsList = storeSectionsList;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", storeName='" + storeName + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", map='" + map + '\'' +
                ", userList=" + userList +
                ", storeSectionsList=" + storeSectionsList +
                '}';
    }
}
