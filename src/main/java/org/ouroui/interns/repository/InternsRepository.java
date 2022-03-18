package org.ouroui.interns.repository;

import org.ouroui.interns.model.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternsRepository extends JpaRepository<Internship,Long> {
    List<Internship> findInternshipByDuration(int duration);
    List<Internship> findInternshipByRoleContaining(String role);
}
