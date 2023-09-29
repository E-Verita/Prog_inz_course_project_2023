package lv.venta.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.models.Thesis;
import lv.venta.models.ThesisApplication;
import lv.venta.models.users.Student;
import lv.venta.repos.IThesisApplicationRepo;
import lv.venta.services.IThesisAplicationService;

@Service
public class ThesisApplicationServiceImplWithDB  implements IThesisAplicationService {

	@Autowired
	private IThesisApplicationRepo applicationRepo;
	
	@Override
	public void insertNewThesisApplication(Thesis thesis, Student student,  String aim,  String tasks) {
		applicationRepo.save(new ThesisApplication(thesis, student,  aim,  tasks));
	}

	
}
