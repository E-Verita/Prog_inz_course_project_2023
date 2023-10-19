package lv.venta.services;

import lv.venta.models.Thesis;
import lv.venta.models.ThesisApplication;
import lv.venta.models.users.Student;

public interface IThesisAplicationService {

	ThesisApplication insertNewThesisApplication(Thesis thesis, Student student, String aim, String tasks);

	ThesisApplication getThesisApplicationById(long id) throws Exception;
	


}
