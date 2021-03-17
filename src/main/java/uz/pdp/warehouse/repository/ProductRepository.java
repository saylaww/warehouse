package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.warehouse.entity.Measurement;
import uz.pdp.warehouse.entity.Product;

import java.util.List;

public interface ProductRepository  extends JpaRepository<Product, Integer> {
    boolean existsByNameAndCategoryId(String name, Integer category_id);

    @Query(value = "select active from product where id=:id", nativeQuery = true)
    boolean getActiveById(@Param("id") Integer id);

    List<Product> findAllByActive(boolean active);

}
