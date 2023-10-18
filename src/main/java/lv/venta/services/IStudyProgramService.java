package lv.venta.services;

import java.util.ArrayList;

import lv.venta.models.StudyProgram;

public interface IStudyProgramService {

	public ArrayList<StudyProgram> findAll() throws Exception;

}
