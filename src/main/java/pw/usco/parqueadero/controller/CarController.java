package pw.usco.parqueadero.controller;
import pw.usco.parqueadero.entity.CarEntity;
import pw.usco.parqueadero.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarRepository carRepository;

    @GetMapping
    public List<CarEntity> getAllCars() {
        return carRepository.findAll();
    }
}

