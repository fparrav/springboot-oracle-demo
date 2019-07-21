package cl.voxcom.demo.controller;


import cl.voxcom.demo.model.User;
import cl.voxcom.demo.repository.UserRepository;
import cl.voxcom.demo.utils.rutValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;
/**
 * Created by Felipe Parra V. on July, 2019
 * Email : fparrav@icloud.com
 * Github: fparrav
 */

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers() {
        return userRepository.getAll();
    }


    @GetMapping("/find")
    @ResponseBody
    public ResponseEntity<User> read(@RequestBody User user) {

        String rutdv = user.getRut() + user.getDv();

        if (!rutValidator.validarRut(rutdv)) {
            throw new IllegalArgumentException("Rut Invalido.");
        }
        User userfinded = userRepository.read(user.getRut(), user.getDv());


        return new ResponseEntity<User>(userfinded, HttpStatus.OK);
    }

    @PostMapping(value = "/")
    @ResponseBody
    public ResponseEntity<User> createUser(@RequestBody User user, UriComponentsBuilder builder)  {

        if(!rutValidator.validarRut(user.getRut()+user.getDv()))
        {
            throw new IllegalArgumentException("Rut Invalido.");
        }

        if (user.getRut().isEmpty() || user.getDv().isEmpty() || user.getNombre().isEmpty() || user.getApellido().isEmpty()){
            throw new IllegalArgumentException("No se admiten campos vacios");
        }
        User createdUser = userRepository.create(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/addItem/{rut}").buildAndExpand(user.getRut()).toUri());
        return new ResponseEntity<User>(createdUser,headers, HttpStatus.CREATED);

    }


    @DeleteMapping("/")
    @ResponseBody
    public ResponseEntity<User> deleteItem(@RequestBody User user){
        if(!rutValidator.validarRut(user.getRut()+user.getDv())){
            throw new IllegalArgumentException("Rut Invalido.");
        }
        User deleteUser =  userRepository.delete(user);
        return new ResponseEntity<User>(deleteUser,HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateItem")
    @ResponseBody
    public ResponseEntity<User> updateItem(@RequestBody User user){
        if(!rutValidator.validarRut(user.getRut()+user.getDv()))
        {
            throw new IllegalArgumentException("Rut Invalido.");
        }

        if (user.getRut().isEmpty() || user.getDv().isEmpty() || user.getNombre().isEmpty() || user.getApellido().isEmpty()){
            throw new IllegalArgumentException("No se admiten campos vacios");
        }

        User updatedUser =  userRepository.update(user);

        return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
    }


}