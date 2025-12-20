package com.app.web.marketing.infrastructure;

import com.app.web.marketing.domain.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISubmissionRepository extends JpaRepository<Submission,Long> {
}
