package com.sei.findgo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Arrays;
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
    private String location;

    @Column
    private byte[] map;

    public Store() {
    }

    public Store(int id, String storeName, String location, byte[] map) {
        this.id = id;
        this.storeName = storeName;
        this.location = location;
        this.map = map;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "storeList")
    private List<User> userList;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<StoreSection> storeSectionsList;

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

    public byte[] getMap() {
        return map;
    }

    public void setMap(byte[] map) {
        this.map = map;
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
                ", location='" + location + '\'' +
                ", map=" + Arrays.toString(map) +
                ", userList=" + userList +
                ", storeSectionsList=" + storeSectionsList +
                '}';
    }
}
