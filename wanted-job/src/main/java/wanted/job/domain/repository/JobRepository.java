package wanted.job.domain.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wanted.job.domain.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

	@Query(value = "select j from Job j inner join Company c on j.company.id = c.id "
		+ "where c.name like concat('%',:keyword,'%') or j.position like concat('%',:keyword,'%') "
		+ "or j.skill like concat('%',:keyword,'%')")
	List<Job> findAllByKeyword(String keyword);
}
