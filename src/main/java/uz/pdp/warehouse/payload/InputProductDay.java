package uz.pdp.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.warehouse.entity.Product;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputProductDay {

//    private Product product;
    private Integer value;
    private Double sum;


}
