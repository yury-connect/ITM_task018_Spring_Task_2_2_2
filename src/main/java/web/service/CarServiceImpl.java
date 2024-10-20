package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.CarDao;
import web.model.Car;

import java.util.List;


@Service
public class CarServiceImpl implements CarService{

    private CarDao dao;



    @Autowired
    public CarServiceImpl(CarDao carDao) {
        this.dao = carDao;
    }



    @Override
    public void save(Car car) {
        dao.save(car);
    }


    @Override
    public List<Car> getAll() {
        return dao.getAll()  ;
    }


    /*
    При запросе /cars выводить весь список. При запросе /cars?count=2
    должен отобразиться список из 2 машин, при /cars?count=3 - из 3, и тд.
    При count ≥ 5 выводить весь список машин.
     */
    @Override
    public List<Car> get(Integer count) {
        List<Car> result;
        if (count == null || count >= 5) {
            result = dao.getAll();
        } else {
            List<Car> allCars = dao.getAll();
            result = allCars.subList(0, Math.min(count, allCars.size())); // subList(int fromIndex, int toIndex)
        }
        return result;
    }
}
