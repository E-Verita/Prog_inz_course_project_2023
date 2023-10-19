package lv.venta.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.models.Thesis;
import lv.venta.models.users.Student;
import lv.venta.repos.users.IStudentRepo;
import lv.venta.services.IStudentService;

@Service
public class StudentServiceImplWithDB implements IStudentService{
	
	@Autowired
	private IStudentRepo studentRepo;

	@Override
	public ArrayList<Student> findAll() throws Exception {
		try {
			return (ArrayList<Student>) studentRepo.findAll();
		} catch (Exception e) {
			throw new Exception("Cannot get Student records from DB: " + e.getMessage());
		}
	}

	@Override
	public Student getStudentById(long id) throws Exception {
		// TODO Auto-generated method stub
		if (studentRepo.existsById(id)) {
			return studentRepo.findById(id).get();
		} else {
			throw new Exception("Incorrect Id");
		}
	}

}