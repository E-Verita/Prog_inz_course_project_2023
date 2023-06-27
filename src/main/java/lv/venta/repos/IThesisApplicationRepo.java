package lv.venta.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.models.ThesisApplication;

public interface IThesisApplicationRepo extends CrudRepository<ThesisApplication, Long> {

}