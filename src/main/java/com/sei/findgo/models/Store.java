package com.sei.findgo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private String address;

    @Column
    private String description;

    @Column
    private String floorPlan;

    @Column
    private String storeImg;

    @JsonIgnoreProperties //Changed this
    @ManyToMany(mappedBy = "storeList")
    private List<User> userList;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<StoreSection> storeSectionsList;

    public Store() {
    }

    public Store(String storeName, String address, String description, String floorPlan, String storeImg) {
        this.storeName = storeName;
        this.address = address;
        this.description = description;
        this.floorPlan = floorPlan;
        this.storeImg = storeImg;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFloorPlan() {
        return floorPlan;
    }

    public void setFloorPlan(String floorPlan) {
        this.floorPlan = floorPlan;
    }

    public String getStoreImg() {
        return storeImg;
    }

    public void setStoreImg(String storeImg) {
        this.storeImg = storeImg;
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
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", floorPlan='" + floorPlan + '\'' +
                ", storeImg='" + storeImg + '\'' +
                ", userList=" + userList +
                ", storeSectionsList=" + storeSectionsList +
                '}';
    }
}
