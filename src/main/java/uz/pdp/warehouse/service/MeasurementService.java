package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Category;
import uz.pdp.warehouse.entity.Measurement;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

    public Result addMeasurementService(Measurement measurement){
        boolean existsByName = measurementRepository.existsByName(measurement.getName());
        if (existsByName){
            return new Result("Bunday ulchov birligi mavjud", false);
        }
        measurementRepository.save(measurement);

        return new Result("Muvaffaqiyatli saqlandi", true);
    }

    public List<Measurement> getMes(){
        return measurementRepository.findAllByActive(true);
    }

    public Measurement getOne(Integer id) {
        boolean active = measurementRepository.getActiveById(id);
        if (!active){
            return new Measurement();
        }

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent()){
            return new Measurement();
        }
        Measurement measurement = optionalMeasurement.get();
        return measurement;
    }

    public Result update(Integer id, Measurement measurement) {
        boolean active = measurementRepository.getActiveById(id);
        if (!active){
            return new Result("This Measurement id DeActive", false);
        }

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent()){
            return new Result("Bunday id topilmadi", false);
        }
        Measurement measurementdb = optionalMeasurement.get();
        measurementdb.setName(measurement.getName());

        measurementRepository.save(measurementdb);
        return new Result("Measurement updated", true);
    }

    public Result delete(Integer id) {
        boolean active = measurementRepository.getActiveById(id);
        if (!active){
            return new Result("This Measurement id DeActive", false);
        }

        boolean existsById = measurementRepository.existsById(id);
        if (!existsById){
            return new Result("Bunday id bazada topilmadi", false);
        }
        measurementRepository.deleteById(id);
        return new Result("Measurement o'chirildi", true);
    }
}
