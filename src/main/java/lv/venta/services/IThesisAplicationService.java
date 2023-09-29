package lv.venta.services;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import lv.venta.models.Thesis;
import lv.venta.models.ThesisApplication;
import lv.venta.models.users.Student;

public interface IThesisAplicationService {

	void insertNewThesisApplication(Thesis thesis, Student student, String aim, String tasks);

}
