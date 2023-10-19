package lv.venta.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.models.Area;
import lv.venta.models.Complexity;
import lv.venta.models.Thesis;
import lv.venta.models.ThesisApplication;
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
	public ArrayList<Thesis> selectAllByAssignedStudentIsNull()  throws Exception{
		try {
			return (ArrayList<Thesis>) thesisRepo.findAllByAssignedStudentIsNull();
		} catch (Exception e) {
			throw new Exception("Cannot get unassigned thesis records from DB: " + e.getMessage());
		}
	}

	@Override
	public void updateThesisById(long thesisId, String titleLv, String titleEn, int applications,
			Collection<Area> areas, Complexity complexity, String privateNotes, String publicNotes,
			Student assignedStudent, AcademicPersonel supervisor, Collection<AcademicPersonel> reviewers,
			Collection<ThesisApplication> thesisApplications) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateThesisById(long id, Thesis thesis) throws Exception {
		if (thesisRepo.existsById(id)) {
	        Thesis updatedThesis = thesisRepo.findById(id).get();
		updatedThesis.setTitleLv(thesis.getTitleLv());
        updatedThesis.setTitleEn(thesis.getTitleEn());
        updatedThesis.setApplications(thesis.getApplications());
        updatedThesis.setAreas(thesis.getAreas());
        updatedThesis.setComplexity(thesis.getComplexity());
        updatedThesis.setPrivateNotes(thesis.getPrivateNotes());
        updatedThesis.setPublicNotes(thesis.getPublicNotes());
        updatedThesis.setAssignedStudent(thesis.getAssignedStudent());
        updatedThesis.setSupervisor(thesis.getSupervisor());
        updatedThesis.setReviewers(thesis.getReviewers());
        updatedThesis.setThesisApplications(thesis.getThesisApplications());
        
        thesisRepo.save(updatedThesis);
		} else {
	        throw new Exception("Invalid ID");
	    }
	}

		
}
