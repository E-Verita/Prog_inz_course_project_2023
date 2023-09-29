package lv.venta.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.models.Thesis;
import lv.venta.models.users.AcademicPersonel;
import lv.venta.repos.IThesisRepo;
import lv.venta.repos.users.IAcademicPersonelRepo;
import lv.venta.services.IAcademicPersonelService;

@Service
public class AcademicPersonelServiceImplWithDB implements IAcademicPersonelService{

	@Autowired
	private IAcademicPersonelRepo academicRepo;
	
	@Override
	public ArrayList<AcademicPersonel> findAll() throws Exception {
		try {
			return (ArrayList<AcademicPersonel>) academicRepo.findAll();
		} catch (Exception e) {
			throw new Exception("Cannot get Academic Personel records from DB: " + e.getMessage());
		}
	}
}
