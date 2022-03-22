package org.ouroui.interns.repository;

import org.ouroui.interns.Dto.OrderResponce;
import org.ouroui.interns.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findById(long id);

    //Get comapany name with the role and location of internship associated
    @Query("select new org.ouroui.interns.Dto.OrderResponce(c.Name, i.role) from Company c join c.internshipList i")
    List<OrderResponce> getJoinInfo();
}
