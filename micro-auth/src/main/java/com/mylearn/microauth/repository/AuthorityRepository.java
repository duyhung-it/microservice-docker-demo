package com.mylearn.microauth.repository;

import com.mylearn.microauth.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {

}
