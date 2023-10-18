package lv.venta.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.models.StudyProgram;

public interface IStudyServiceRepo extends CrudRepository <StudyProgram, Long> {

}
