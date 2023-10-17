package lv.venta.services;

import java.util.ArrayList;

import lv.venta.models.users.AcademicPersonel;

public interface IAcademicPersonelService {

	ArrayList<AcademicPersonel> findAll() throws Exception;

	ArrayList<AcademicPersonel> findByUsername(String currentPrincipalName) throws Exception;

}
