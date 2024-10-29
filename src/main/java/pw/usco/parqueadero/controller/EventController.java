package pw.usco.parqueadero.controller;

import org.springframework.web.bind.annotation.*;
import pw.usco.parqueadero.entity.CarEntity;
import pw.usco.parqueadero.entity.EventEntity;
import pw.usco.parqueadero.repository.CarRepository;
import pw.usco.parqueadero.service.CarService;
import pw.usco.parqueadero.service.EventServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("events")
public class EventController {
    private final EventServiceImp eventServiceImp;
    private final CarRepository carRepository;

    @GetMapping
    public String events(Model model) {
        model.addAttribute("events", eventServiceImp.getAll());
        return "events";
    }

    @GetMapping("create")
    public String createForm(Model model) {
        model.addAttribute("event", new EventEntity());
        model.addAttribute("cars", carRepository.findAll());
        return "create_event";
    }

    @PostMapping("create")
    public String save(@ModelAttribute EventEntity event, @RequestParam Long carId) {
        CarEntity car = carRepository.findById(carId) .orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + carId));
        event.setCar(car);
        eventServiceImp.create(event);
        return "redirect:/events";
    }

    @GetMapping("edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        EventEntity event = eventServiceImp.getById(id);
        model.addAttribute("event", event);
        return "edit_event";
    }

    @PostMapping("edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute EventEntity event) {
        eventServiceImp.update(id, event);
        return "redirect:/events";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable Long id) {
        eventServiceImp.delete(id);
        return "redirect:/events";
    }
}
