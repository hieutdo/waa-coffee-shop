package edu.mum.coffee.repository;

import edu.mum.coffee.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
