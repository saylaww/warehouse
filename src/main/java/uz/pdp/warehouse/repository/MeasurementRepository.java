package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.warehouse.entity.Measurement;
import uz.pdp.warehouse.entity.Warehouse;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

    boolean existsByName(String name);

    @Query(value = "select active from measurement where id=:id", nativeQuery = true)
    boolean getActiveById(@Param("id") Integer id);

    List<Measurement> findAllByActive(boolean active);

}
