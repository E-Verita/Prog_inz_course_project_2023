package lv.venta.services;

import java.util.ArrayList;

import lv.venta.models.users.Student;

public interface IStudentService {

	ArrayList<Student> findAll() throws Exception;

	Student getStudentById(long studentId) throws Exception;

	Student findByUsername(String currentPrincipalName) throws Exception; 

}
