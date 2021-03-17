package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.warehouse.entity.User;
import uz.pdp.warehouse.entity.Warehouse;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByPhoneNumberAndCode(String phoneNumber, String code);

    @Query(value = "select active from users where id=:id", nativeQuery = true)
    boolean getActiveById(@Param("id") Integer id);

    List<User> findAllByActive(boolean active);

}
