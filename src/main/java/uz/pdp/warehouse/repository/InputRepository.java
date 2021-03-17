package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.warehouse.entity.Input;
import uz.pdp.warehouse.entity.Product;
import uz.pdp.warehouse.payload.InputProductDay;

import java.sql.Timestamp;
import java.util.Optional;

public interface InputRepository extends JpaRepository<Input, Integer> {

    boolean existsByCodeAndFactureNumber(String code, String factureNumber);

    Optional<Input> findByDate(Timestamp date);

    @Query(value = "select * from input where date =:sane", nativeQuery = true)
    Optional<Input> getbySane(Timestamp sane);
//    boolean findByDate(Timestamp date);
}
