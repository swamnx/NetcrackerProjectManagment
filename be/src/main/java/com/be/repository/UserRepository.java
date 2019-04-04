package com.be.repository;

import com.be.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findAll();
    User findUserByEmail(String email);
    User findUserByIdUser(Integer id);
    User save(User user);
    void delete(User user);
    User findUserByEmailAndPassword(String email,String password);
}
