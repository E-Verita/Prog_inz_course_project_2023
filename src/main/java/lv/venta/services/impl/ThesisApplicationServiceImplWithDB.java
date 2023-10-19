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
	public ThesisApplication insertNewThesisApplication(Thesis thesis, Student student,  String aim,  String tasks) {
		return applicationRepo.save(new ThesisApplication(thesis, student,  aim,  tasks));
	}

	@Override
	public ThesisApplication getThesisApplicationById(long id) throws Exception {
		if (applicationRepo.existsById(id)) {
			return applicationRepo.findById(id).get();
		} else {
			throw new Exception("Incorrect Id");
		}
	}
	
}
