package wanted.job.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.job.domain.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
