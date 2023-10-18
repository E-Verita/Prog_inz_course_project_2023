package lv.venta.services;

import lv.venta.models.Thesis;
import lv.venta.models.users.Student;

public interface IThesisAplicationService {

	void insertNewThesisApplication(Thesis thesis, Student student, String aim, String tasks);

}
