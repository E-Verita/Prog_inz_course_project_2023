package lv.venta.repo.security;

import org.springframework.data.repository.CrudRepository;

import lv.venta.models.users.User;

public interface IMyUserRepo extends CrudRepository<User, Integer>  {

	User findByUsername(String username);

}
