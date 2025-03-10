
package com.taskApp.taskApp.service;

import com.taskApp.taskApp.model.AppUser;
import com.taskApp.taskApp.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{
    //realizar metodo CRUD de user
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Optional<AppUser> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    
    public List<AppUser> getAllUsers(){
        return userRepository.findAll();
    }
    public AppUser getUser(Long id){
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
    }
    public List<AppUser> searchUsers(String mail, String firstname, String lastname, String username ){
        //get para admins
        AppUser userProbe = new AppUser();
        userProbe.setFirstname(firstname);
        userProbe.setLastname(lastname);
        userProbe.setMail(mail);
        userProbe.setUsername(username);
        
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreNullValues().withIgnoreCase();
        Example<AppUser> example = Example.of(userProbe, matcher);
        return userRepository.findAll(example);
    }
    public void saveUser(AppUser user){
            // Codificar la contraseña antes de guardar el usuario
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            // Guardar el usuario en el repositorio
            userRepository.save(user);
    }
    public void updateUser(AppUser user){
        saveUser(user);
    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("username does not exist"));
        //get the values from AppUser and create a User Object
        String nameUser = appUser.getUsername();
        String password = appUser.getPassword();
        Set<String>authorities = appUser.getAuthorities();
        Set<GrantedAuthority>grantedAuthorities = authorities.stream()
                .map(rol -> new SimpleGrantedAuthority(rol))
                .collect(Collectors.toSet());
        //itearte through authorities object
        
        //java 8 map
        //authorities.stream().map(str->str.length()).collect(Collectors.toList());
        grantedAuthorities = authorities.stream()
                .map(rol->new SimpleGrantedAuthority(rol))
                .collect(Collectors.toSet());
        
        //pass the properties
        User user = new User(nameUser, password, grantedAuthorities);
        return user;
    }
}
