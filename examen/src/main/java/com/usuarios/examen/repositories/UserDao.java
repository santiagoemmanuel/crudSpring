package com.usuarios.examen.repositories;

import org.springframework.data.repository.CrudRepository;

import com.usuarios.examen.models.User;

public interface UserDao extends CrudRepository<User ,String > {

}
