package com.app.web.marketing.infrastructure;

import com.app.web.marketing.domain.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFormRepository extends JpaRepository<Form, Long> {
}
