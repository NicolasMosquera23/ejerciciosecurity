package pw.usco.parqueadero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.usco.parqueadero.entity.CarEntity;
import pw.usco.parqueadero.repository.CarRepository;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<CarEntity> getAllCars() {
        return carRepository.findAll();
    }
}
