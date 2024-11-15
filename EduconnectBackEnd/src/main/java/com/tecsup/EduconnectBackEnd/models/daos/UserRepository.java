package com.tecsup.EduconnectBackEnd.models.daos;

import com.tecsup.EduconnectBackEnd.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
