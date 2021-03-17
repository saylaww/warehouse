package uz.pdp.warehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.warehouse.DateUtil;
import uz.pdp.warehouse.payload.DailyTotal;
import uz.pdp.warehouse.repository.InputProductRepository;

import java.util.Date;

@Service
public class DashboardService {

    final InputProductRepository inputProductRepository;

    public DashboardService(InputProductRepository inputProductRepository) {
        this.inputProductRepository = inputProductRepository;
    }

    public DailyTotal getDashboard1(){
        Date startOfDay = DateUtil.atStartOfDay(new Date());

        DailyTotal dailyTotal =  inputProductRepository.findDailyInput(startOfDay);
//        DailyTotal dailyTotal = DailyTotal.class.cast(objects[0]);

//        DailyTotal dailyTotal = new DailyTotal();
//        dailyTotal.setTotalPrice((Double) objects[1]);
//        dailyTotal.setTotalAmount((Double) objects[0]);
        return dailyTotal;
    }
}
