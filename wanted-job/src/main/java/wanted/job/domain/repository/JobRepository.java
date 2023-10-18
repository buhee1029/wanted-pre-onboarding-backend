package wanted.job.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.job.domain.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
}
