package lv.venta.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.models.users.MeetingMember;

public interface IMeetingMemberRepo extends CrudRepository<MeetingMember, Long> {

}