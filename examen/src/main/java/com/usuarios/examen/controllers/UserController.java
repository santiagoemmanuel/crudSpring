package com.usuarios.examen.controllers;

import java.time.LocalDate;
import java.time.Period;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuarios.examen.models.User;
import com.usuarios.examen.repositories.UserDao;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private UserDao userDado;

    @Autowired
    private PasswordEncoder  passwordEncoder;
    
    
    // http://localhost:8080/api/user/santiago
    @GetMapping("users/{correo}")
    public User user(@PathVariable String correo) {
        Optional<User> opt = this.userDado.findById(correo);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    // http://localhost:8080/api/users
    @GetMapping("users")
    public Iterator<User> users() {
        return this.userDado.findAll().iterator();
    }

    // http://localhost:8080/api/user
    @PostMapping("registrar")
    public ResponseEntity<?> crearUser(@Valid @RequestBody User user) {

        if (Period.between(user.getNacimiento(), LocalDate.now()).getYears() >= 18) {

            String passEncripted = passwordEncoder.encode(user.getPassword()) ; 
            user.setPassword(passEncripted);

            return ResponseEntity.status(HttpStatus.CREATED).body(this.userDado.save(user));
        } else {            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no ha cumplido mayoria de edad");
        }

    }

    // http://localhost:8080/api/user/santiago
    @DeleteMapping("users/{correo}")
    public User deleteUser(@PathVariable String correo) {

        Optional<User> opt = this.userDado.findById(correo);

        if (opt.isPresent()) {
            this.userDado.deleteById(correo);
            return opt.get();
        }
        return null;
    }

    @PutMapping("users/{correo}")
    public User updateUser(@PathVariable String correo, @RequestBody User userDetails) {

        Optional<User> opt = this.userDado.findById(correo);

        if (opt.isPresent()) {
            User existingUser = opt.get();

            existingUser.setDirecion(userDetails.getDirecion());
            existingUser.setNombre(userDetails.getNombre());
            existingUser.setApellidos(userDetails.getApellidos());

            return this.userDado.save(existingUser);
        }

        return null;
    }







    @GetMapping("test")
    public User test() {
        User user = new User();
        user.setNombre("German");
        user.setApellidos("Santiago G.");
        user.setEmail("santiago@gmail.com");

        return user;
    }

    // sudo docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=abcd1234 -p
    // 3306:3306 -d mysql:latest


}
