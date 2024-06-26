package com.nrecinos.preparcial.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.ListCrudRepository;
import com.nrecinos.preparcial.models.entities.Token;
import com.nrecinos.preparcial.models.entities.User;

public interface TokenRepository 
extends ListCrudRepository<Token, UUID>{ 

List<Token> findByUserAndActive(User user, Boolean active);

}
