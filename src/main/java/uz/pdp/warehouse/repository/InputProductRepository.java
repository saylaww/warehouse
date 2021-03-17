package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.warehouse.entity.InputProduct;
import uz.pdp.warehouse.entity.Product;

import uz.pdp.warehouse.payload.DailyTotal;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

public interface InputProductRepository extends JpaRepository<InputProduct, Integer> {
//    Optional<InputProduct> findByInputId(Integer input_id);
//    InputProduct findBy

//    Product findByInput_Date(Timestamp input_date);

    @Query(value = "select new uz.pdp.warehouse.payload.DailyTotal(sum(ip.amount), sum(ip.price)) from InputProduct ip where ip.input.date between :date and current_date")
    DailyTotal findDailyInput(Date date);
}
