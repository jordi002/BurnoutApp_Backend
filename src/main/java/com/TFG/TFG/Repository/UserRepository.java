package com.TFG.TFG.Repository;

import com.TFG.TFG.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository extends PagingAndSortingRepository<User, String> {
    User findById(Long id);
    User findByMUsername(String Username);
    User findByMEmail(String Email);
    Integer deleteUserById(Long id);
    void deleteAll();
    List<User> findAll();
}
