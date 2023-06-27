package lv.venta.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.models.Thesis;

public interface IThesisRepo extends CrudRepository <Thesis, Long> {

	ArrayList<Thesis> findAllBySupervisor_Idp(long supervisorId);


}
