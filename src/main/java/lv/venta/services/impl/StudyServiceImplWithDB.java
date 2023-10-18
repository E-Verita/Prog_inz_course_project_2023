package lv.venta.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.models.StudyProgram;
import lv.venta.repos.IStudyServiceRepo;
import lv.venta.services.IStudyProgramService;

@Service
public class StudyServiceImplWithDB implements IStudyProgramService {
	
	@Autowired
	private IStudyServiceRepo studyRepo;

	@Override
	public ArrayList<StudyProgram> findAll() throws Exception {
		try {
			return (ArrayList<StudyProgram>) studyRepo.findAll();
		} catch (Exception e) {
			throw new Exception("Cannot get Study Program records from DB: " + e.getMessage());
		}
	}

}
