package com.example.storecoreinfoapi;

import com.example.storecoreinfoapi.dao.StoreDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StoreRepository extends JpaRepository<StoreDAO, UUID> {

}
