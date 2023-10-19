package lv.venta.services;

import java.util.ArrayList;
import java.util.Collection;

import lv.venta.models.Area;
import lv.venta.models.Complexity;
import lv.venta.models.StudyProgram;
import lv.venta.models.Thesis;
import lv.venta.models.ThesisApplication;
import lv.venta.models.users.AcademicPersonel;
import lv.venta.models.users.Student;

public interface IThesisService {
	ArrayList <Thesis> selectAllThesis() throws Exception;
	
	ArrayList <Thesis> selectAllThesisBySupervisor(long supervisorId) throws Exception;

	Thesis getThesisById(long id) throws Exception;

	void deleteThesisById(long id)  throws Exception;

	ArrayList<Thesis> selectAllByAssignedStudentIsNull() throws Exception;

	void insertNewThesis(String titleLv, String titleEn, Collection<Area> areas, Complexity complexity,
			String publicNotes, Collection<StudyProgram> programs, AcademicPersonel supervisor);

	void updateThesisById(long thesisId, String titleLv, String titleEn, int applications, Collection<Area> areas,
			Complexity complexity, String privateNotes, String publicNotes, Student assignedStudent,
			AcademicPersonel supervisor, Collection<AcademicPersonel> reviewers,
			Collection<ThesisApplication> thesisApplications) throws Exception;

	void updateThesisById(long thesisId, Thesis thesis) throws Exception;

	

}
