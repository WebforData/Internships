package org.ouroui.interns.repository;

import org.ouroui.interns.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    List<Company> findCompanyByNameContaining(String name);
}
