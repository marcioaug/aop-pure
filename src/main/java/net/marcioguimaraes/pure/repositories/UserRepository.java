package net.marcioguimaraes.pure.repositories;

import net.marcioguimaraes.pure.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{ }
