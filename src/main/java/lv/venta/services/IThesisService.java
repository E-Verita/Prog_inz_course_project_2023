package lv.venta.services;

import java.util.ArrayList;

import lv.venta.models.Thesis;

public interface IThesisService {
	ArrayList <Thesis> selectAllThesis() throws Exception;
	
	ArrayList <Thesis> selectAllThesisBySupervisor(long supervisorId) throws Exception;
	

}
