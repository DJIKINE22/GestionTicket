package com.GestionTicket.applications.MyTicket.Services;

import com.GestionTicket.applications.MyTicket.Dto.UserDto;
import com.GestionTicket.applications.MyTicket.Entities.Compagnie;
import com.GestionTicket.applications.MyTicket.Entities.User;
import com.GestionTicket.applications.MyTicket.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserDto userDto) {
        User utilisateur = new User(userDto.getNom(),userDto.getPrenom(),userDto.getAdresse(),userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()),userDto.getRole(),userDto.getTelephone());
        return userRepository.save(utilisateur);
    }
    public List<User> allCompa(){
        return  this.userRepository.findAll();
    }

    public User getUsrById(Long id) {
        Optional< User > optional = userRepository.findById(id);
        User user = null;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException(" Compagnie not found for id :: " + id);
        }
        return user;
    }

    public User modifierUtilisateur(Long id, User utilisateurModifie) {
        User utilisateurExist = userRepository.getById(id);

        // Mettez à jour les attributs de l'utilisateur existant avec les nouvelles valeurs
        utilisateurExist.setId(utilisateurModifie.getId());
        utilisateurExist.setNom(utilisateurModifie.getNom());
        utilisateurExist.setPrenom(utilisateurModifie.getPrenom());
        utilisateurExist.setAdresse(utilisateurExist.getAdresse());
        utilisateurExist.setEmail(utilisateurModifie.getEmail());
        utilisateurExist.setRole(utilisateurExist.getRole());


        return userRepository.save(utilisateurExist);
    }
    public void deleteUserById(Long id) {
        userRepository.deleteById( id);
    }

}