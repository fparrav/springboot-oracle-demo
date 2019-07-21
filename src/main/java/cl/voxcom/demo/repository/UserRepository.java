package cl.voxcom.demo.repository;

import cl.voxcom.demo.model.User;

import java.util.List;

/**
 * Created by Felipe Parra V. on July, 2019
 * Email : fparrav@icloud.com
 * Github: fparrav
 */
public interface UserRepository {
    User create(User user);
    User read(String rut, String dv);
    User update (User user);
    User delete(User user);

    List<User> getAll();
}
