package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Measurement;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;

    @PostMapping
    public Result addMeasurementController(@RequestBody Measurement measurement){
        Result result = measurementService.addMeasurementService(measurement);
        return result;
    }

    //Get list,
    @GetMapping
    public List<Measurement> getMeasurements(){
        List<Measurement> measurementList = measurementService.getMes();
        return measurementList;
    }

    // Get one,
    @GetMapping("/{id}")
    public Measurement getMeasurement(@PathVariable Integer id){
        Measurement measurement = measurementService.getOne(id);
        return measurement;
    }

    // Edit,
    @PutMapping("/{id}")
    public Result updateMeasurement(@PathVariable Integer id, @RequestBody Measurement measurement){
        Result result = measurementService.update(id, measurement);
        return result;
    }


    // Delete
    @DeleteMapping("/{id}")
    public Result deleteMeasurement(@PathVariable Integer id){
        Result result = measurementService.delete(id);
        return result;
    }


}
