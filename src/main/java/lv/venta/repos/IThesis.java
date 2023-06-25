package lv.venta.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.models.Course;
import lv.venta.models.Thesis;

public interface IThesis extends CrudRepository <Thesis, Long> {

}
