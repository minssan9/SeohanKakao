package com.message.auth.mapper;

import com.message.auth.domain.OrgUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgUserRepository extends JpaRepository<OrgUser, String> {
    OrgUser findByEmpid(String empid);
}
