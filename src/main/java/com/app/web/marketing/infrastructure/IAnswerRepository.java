package com.app.web.marketing.infrastructure;

import com.app.web.marketing.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnswerRepository extends JpaRepository<Answer,Long> {

}
