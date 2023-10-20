package com.GestionTicket.applications.MyTicket.Repository;

import com.GestionTicket.applications.MyTicket.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
