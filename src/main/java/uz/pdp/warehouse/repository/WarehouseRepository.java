package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.warehouse.entity.Warehouse;

import java.util.List;
import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

    boolean existsByName(String name);

//    @Query("select * from ", nativeQuery = true)

    @Query(value = "select active from warehouse where id=:id", nativeQuery = true)
    boolean getActiveById(@Param("id") Integer id);

    List<Warehouse> findAllByActive(boolean active);

}
