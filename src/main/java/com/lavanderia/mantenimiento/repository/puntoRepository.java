
package com.lavanderia.mantenimiento.repository;

import com.lavanderia.mantenimiento.model.punto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface puntoRepository extends JpaRepository<punto, Long>{
    
}
