package com.POS.repository;

import com.POS.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByMobileNumber(String mobileNumber);

    boolean existsByMobileNumber(String mobileNumber);
}
