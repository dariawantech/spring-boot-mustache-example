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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import javax.sql.DataSource;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceJPATest {

    @Autowired 
    private DataSource dataSource;
    
    @Autowired 
    private UserService userService;
    
    @Before
    public void cleanTestData() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "delete from users where email not like ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%@cappucino.com");
            ps.executeUpdate();
        }
    }    
    
    @Test
    public void testFindAllUsers() {
        List<User> users = userService.findAll(1, 20);
        assertNotNull(users);
        assertTrue(users.size() == 6);
        for (User user : users) {
            assertNotNull(user.getId());
            assertNotNull(user.getName());
            assertNotNull(user.getEmail());
        }
    }
    
    @Test
    public void testSaveUpdateDeleteUser() throws Exception{
        User u = new User();
        u.setName("Charlize Theron");
        u.setEmail("charlize@mocha.com");
        
        userService.save(u);
        assertNotNull(u.getId());
        
        User findUser = userService.findById(u.getId());
        assertEquals(u.getName(), findUser.getName());
        assertEquals(u.getEmail(), findUser.getEmail());
        
        // update record
        u.setEmail("charlize@latte.net");
        userService.update(u);
        
        // test after update
        findUser = userService.findById(u.getId());
        assertEquals(u.getEmail(), findUser.getEmail());
        
        // test delete
        userService.deleteById(u.getId());
        
        // query after delete
        User uDel = userService.findById(u.getId());
        assertNull(uDel);
    }
}
