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
public class AcademicPersonelServiceImplWithDB implements IAcademicPersonelService {

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

	@Override
	public ArrayList<AcademicPersonel> findByUsername(String currentPrincipalName) throws Exception {
		try {
		 String[] nameAndSurname = currentPrincipalName.split("\\.");
		    String name = nameAndSurname[0];
		    String surname = nameAndSurname[1];
		    System.out.println(name + " " + surname );
		    return academicRepo.findBySurnameIgnoreCaseAndNameIgnoreCase(surname, name);
		
	} catch (Exception e) {
		throw new Exception("Cannot get Academic Personel records from DB: " + e.getMessage());
	}
}
}