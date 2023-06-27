package lv.venta.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.models.Thesis;
import lv.venta.repos.IThesisRepo;
import lv.venta.services.IThesisService;

@Service
public class ThesisServiceImplWithDB implements IThesisService{
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
	        throw new Exception("Cannot get Thesis records for Supervisor " + supervisorId  + " from DB: " + e.getMessage());
	    }
	}
	

}
