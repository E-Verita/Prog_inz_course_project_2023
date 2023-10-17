package lv.venta.repos.users;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.models.users.AcademicPersonel;


public interface IAcademicPersonelRepo  extends CrudRepository <AcademicPersonel, Long> {

	ArrayList<AcademicPersonel> findBySurnameIgnoreCaseAndNameIgnoreCase(String surname, String name);

}
