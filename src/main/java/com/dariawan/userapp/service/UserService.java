/**
 * Spring Boot + Mustache Example  (https://www.dariawan.com)
 * Copyright (C) 2019 Dariawan <hello@dariawan.com>
 *
 * Creative Commons Attribution-ShareAlike 4.0 International License
 *
 * Under this license, you are free to:
 * # Share - copy and redistribute the material in any medium or format
 * # Adapt - remix, transform, and build upon the material for any purpose,
 *   even commercially.
 *
 * The licensor cannot revoke these freedoms
 * as long as you follow the license terms.
 *
 * License terms:
 * # Attribution - You must give appropriate credit, provide a link to the
 *   license, and indicate if changes were made. You may do so in any
 *   reasonable manner, but not in any way that suggests the licensor
 *   endorses you or your use.
 * # ShareAlike - If you remix, transform, or build upon the material, you must
 *   distribute your contributions under the same license as the original.
 * # No additional restrictions - You may not apply legal terms or
 *   technological measures that legally restrict others from doing anything the
 *   license permits.
 *
 * Notices:
 * # You do not have to comply with the license for elements of the material in
 *   the public domain or where your use is permitted by an applicable exception
 *   or limitation.
 * # No warranties are given. The license may not give you all of
 *   the permissions necessary for your intended use. For example, other rights
 *   such as publicity, privacy, or moral rights may limit how you use
 *   the material.
 *
 * You may obtain a copy of the License at
 *   https://creativecommons.org/licenses/by-sa/4.0/
 *   https://creativecommons.org/licenses/by-sa/4.0/legalcode
 */
package com.dariawan.userapp.service;

import com.dariawan.userapp.entity.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.dariawan.userapp.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    private boolean existsById(Long id) {
        return userRepository.existsById(id);
    }
    
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    public List<User> findAll(int pageNumber, int rowPerPage) {
        List<User> users = new ArrayList<>();
        Pageable sortedByLastUpdateDesc = PageRequest.of(pageNumber - 1, rowPerPage, 
                Sort.by("id").ascending());
        userRepository.findAll(sortedByLastUpdateDesc).forEach(users::add);
        return users;
    }
    
    public User save(User user) throws Exception {
        if (StringUtils.isEmpty(user.getName())) {
            throw new Exception("Name is required");
        }
        if (StringUtils.isEmpty(user.getEmail())) {
            throw new Exception("Email is required");
        }
        if (user.getId() != null && existsById(user.getId())) { 
            throw new Exception("User with id: " + user.getId() + " already exists");
        }
        return userRepository.save(user);
    }
    
    public void update(User user) throws Exception {
        if (StringUtils.isEmpty(user.getName())) {
            throw new Exception("Name is required");
        }
        if (StringUtils.isEmpty(user.getEmail())) {
            throw new Exception("Email is required");
        }
        if (!existsById(user.getId())) {
            throw new Exception("Cannot find User with id: " + user.getId());
        }
        userRepository.save(user);
    }
    
    public void deleteById(Long id) throws Exception {
        if (!existsById(id)) { 
            throw new Exception("Cannot find User with id: " + id);
        }
        else {
            userRepository.deleteById(id);
        }
    }
    
    public Long count() {
        return userRepository.count();
    }
}
