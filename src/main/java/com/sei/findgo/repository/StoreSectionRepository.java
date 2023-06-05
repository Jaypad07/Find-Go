package com.sei.findgo.repository;

import com.sei.findgo.models.StoreSection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreSectionRepository extends JpaRepository<StoreSection, Integer> {
    StoreSection findStoreSectionBySectionNameIgnoreCase(String sectionName);
}
