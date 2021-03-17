package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouse.entity.Output;

public interface OutputRepository extends JpaRepository<Output, Integer> {

    boolean existsByCodeAndFactureNumber(String code, String factureNumber);
}
