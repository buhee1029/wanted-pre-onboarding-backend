package wanted.job.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.job.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
