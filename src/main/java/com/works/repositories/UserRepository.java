package com.works.repositories;

import com.works.entities.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<CustomUser, Long>
{
    CustomUser findByusername(String username);
}
