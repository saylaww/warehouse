package uz.pdp.warehouse.payload;

import lombok.Data;

@Data
public class OutputProductDto {

    private Integer productId;

    private Double anmount;

    private Double price;

    private Integer outputId;


}
