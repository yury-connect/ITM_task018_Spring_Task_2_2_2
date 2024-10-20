package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.service.CarService;
import web.util.CarUtils;


@Controller
@RequestMapping(value = "/cars")
public class CarController {

    private CarService service;
    private int numDisplayedCars; // Количество машин на странице 'cars', отображаемое в данный момент



    @Autowired
    public CarController(CarService service) {
        this.service = service;
    }



    @GetMapping()
    public String showCarsPage(@RequestParam(value = "count", required = false, defaultValue = "0") Integer numCars, Model model) {
        this.numDisplayedCars = numCars;
        model.addAttribute("selection_cars", service.get(numCars));
        model.addAttribute("all_existing_cars", service.getAll());
        model.addAttribute("num_displayed_cars", numDisplayedCars);
        return "cars";
    }


    @PostMapping("/generate")
    public String generateCar(@ModelAttribute("car_count") Integer count) {
        CarUtils.generateCar(count).stream().forEach(car -> service.save(car));
        return "redirect:/cars";
    }
}
