package com.GestionTicket.applications.MyTicket.Services;

import com.GestionTicket.applications.MyTicket.Dto.UserDto;
import com.GestionTicket.applications.MyTicket.Entities.User;
import org.springframework.security.core.context.SecurityContextHolder;

public interface UserService {
    User save(UserDto userDto);

}
