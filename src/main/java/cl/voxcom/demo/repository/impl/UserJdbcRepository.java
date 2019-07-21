package cl.voxcom.demo.repository.impl;

import cl.voxcom.demo.error.ApiNotFoundException;
import cl.voxcom.demo.model.User;
import cl.voxcom.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Felipe Parra V. on July, 2019
 * Email : fparrav@icloud.com
 * Github: fparrav
 */


@Repository
public class UserJdbcRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<User> getAll() {
        List<User> lista = null;
        String sql = "SELECT * FROM USUARIO";

        try {
            lista = jdbcTemplate.query(sql,
                    (resultSet, rowNum) ->
                            new User(
                                    resultSet.getString("rut"),
                                    resultSet.getString("dv"),
                                    resultSet.getString("nombre"),
                                    resultSet.getString("apellido")
                            ));

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            throw e;

        }

        return lista;
    }

    @Override
    public User read(String rut, String dv) {
        User user = null;
        String query = "SELECT * FROM usuario WHERE RUT=?";

        try {
            user = jdbcTemplate.queryForObject(query, new Object[]{rut}, new BeanPropertyRowMapper<>(User.class));

        } catch (Exception e) {
            throw new ApiNotFoundException("Usuario : " + rut + "-" + dv + " No encontrado");
        }
        return user;

    }

    @Override
    public User create(User user) {

        try {
            String sql = "INSERT INTO USUARIO  VALUES (?,?,?,?)";

            jdbcTemplate.update(sql, new Object[]{user.getRut(), user.getDv(), user.getNombre(), user.getApellido()});


        } catch (DuplicateKeyException e) {
            System.err.println(e.getLocalizedMessage().toString());
            throw new DuplicateKeyException("Usuario con rut : " + user.getRut() + "-" + user.getDv() + " ya existe.");
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage().toString());
            throw e;

        }

        return user;

    }

    @Override
    public User update(User user) {
        try {
            String query = "UPDATE USUARIO SET NOMBRE=?, APELLIDO=? WHERE RUT =?";


            jdbcTemplate.update(query, new Object[]{user.getNombre(), user.getApellido(), user.getRut(), });

        }  catch (Exception e) {
            System.err.println(e.getLocalizedMessage().toString());
            throw e;
        }

        return user;

    }

    @Override
    public User delete(User user) {

        String query = "DELETE FROM USUARIO WHERE RUT =?";
        try {

            int size = jdbcTemplate.update(query, user.getRut());
            if (size == 0) {
                throw new ApiNotFoundException("No se encuentra usuario :" + user.getRut() + "-" + user.getDv() + "a borrar");
            }
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            throw e;
        }


        return user;
    }
}