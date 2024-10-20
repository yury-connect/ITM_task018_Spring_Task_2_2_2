package web.dao;

import web.model.Car;

import java.util.List;


public interface CarDao {

    void save(Car car);

    Car getById(int id);

    List<Car> getAll();
}
