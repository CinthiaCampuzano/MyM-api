package com.mym.point.repository;

import com.mym.point.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    @Query(value = "SELECT c FROM ClientEntity c WHERE c.name = :name")
    ClientEntity findAByName(@Param("name") String name);

    boolean existsByName(@Param("name") String name);

}
