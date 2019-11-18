package com.dariawan.userapp.repository;

import com.dariawan.userapp.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long>, 
        JpaSpecificationExecutor<User> {
}
