package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.warehouse.entity.Product;
import uz.pdp.warehouse.entity.Supplier;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    boolean existsByPhoneNumber(String phoneNumber);

    @Query(value = "select active from supplier where id=:id", nativeQuery = true)
    boolean getActiveById(@Param("id") Integer id);

    List<Supplier> findAllByActive(boolean active);


}
