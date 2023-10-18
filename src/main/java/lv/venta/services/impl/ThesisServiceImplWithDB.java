package lv.venta.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.models.Area;
import lv.venta.models.Complexity;
import lv.venta.models.Thesis;
import lv.venta.models.users.AcademicPersonel;
import lv.venta.models.users.Student;
import lv.venta.repos.IThesisRepo;
import lv.venta.services.IThesisService;
import lv.venta.models.StudyProgram;


@Service
public class ThesisServiceImplWithDB implements IThesisService {
	@Autowired
	private IThesisRepo thesisRepo;

	@Override
	public ArrayList<Thesis> selectAllThesis() throws Exception {
		try {
			return (ArrayList<Thesis>) thesisRepo.findAll();
		} catch (Exception e) {
			throw new Exception("Cannot get Thesis records from DB: " + e.getMessage());
		}
	}

	@Override
	public ArrayList<Thesis> selectAllThesisBySupervisor(long supervisorId) throws Exception {
		try {
			return thesisRepo.findAllBySupervisor_Idp(supervisorId);
		} catch (Exception e) {
			throw new Exception(
					"Cannot get Thesis records for Supervisor " + supervisorId + " from DB: " + e.getMessage());
		}
	}

	@Override
	public void insertNewThesis(String titleLv, String titleEn, Collection<Area> areas, Complexity complexity,
			String publicNotes, Collection<StudyProgram> programs, AcademicPersonel supervisor) {
		thesisRepo.save(new Thesis(titleLv, titleEn, areas, complexity, publicNotes, programs, supervisor));

	}

	@Override
	public Thesis getThesisById(long id) throws Exception {
		if (thesisRepo.existsById(id)) {
			return thesisRepo.findById(id).get();
		} else {
			throw new Exception("Incorrect Id");
		}
	}

	@Override
	public void deleteThesisById(long id) throws Exception {
		if (thesisRepo.existsById(id)) {
			thesisRepo.deleteById(id);
		} else {
			throw new Exception("Incorrect Id");
		}
	}

	@Override
	public void updateThesisById(long id, String titleLv, String titleEn, Collection<Area> areas, Complexity complexity,
			String privateNotes, String publicNotes,  Collection<StudyProgram> programs, Student assignedStudent, AcademicPersonel supervisor) throws Exception {
		if (thesisRepo.existsById(id)) {
	        Thesis updatedThesis = thesisRepo.findById(id).get();
	        updatedThesis.setTitleLv(titleLv);
	        updatedThesis.setTitleEn(titleEn);
	        updatedThesis.setAreas(areas);
	        updatedThesis.setComplexity(complexity);
	        updatedThesis.setPrivateNotes(privateNotes);
	        updatedThesis.setPublicNotes(publicNotes);
	        updatedThesis.setProgramms(programs);
	        updatedThesis.setAssignedStudent(assignedStudent);
	        updatedThesis.setSupervisor(supervisor);
	        thesisRepo.save(updatedThesis);
	    } else {
	        throw new Exception("Invalid ID");
	    }
	}

	@Override
	public ArrayList<Thesis> selectAllByAssignedStudentIsNull()  throws Exception{
		try {
			return (ArrayList<Thesis>) thesisRepo.findAllByAssignedStudentIsNull();
		} catch (Exception e) {
			throw new Exception("Cannot get unassigned thesis records from DB: " + e.getMessage());
		}
	}
	
	
}
