package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;


@Repository
public class CarDaoImplList implements CarDao {

    private List<Car> cars;
    private int counterId;



    public CarDaoImplList() {
        this.cars = new ArrayList<>();
    }



    @Override
    public void save(Car car) {
        car.setId(counterId++);
        cars.add(car);
    }


    @Override
    public Car getById(int id) {
        Car car = cars.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        return car;
    }


    @Override
    public List<Car> getAll() {
        return cars;
    }
}
