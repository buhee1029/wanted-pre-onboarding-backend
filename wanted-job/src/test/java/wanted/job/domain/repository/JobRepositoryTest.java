package wanted.job.domain.repository;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import wanted.job.domain.entity.Company;
import wanted.job.domain.entity.Job;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class JobRepositoryTest {

	private static final int UPDATE_COMPENSATION = 2000000;
	private static final String UPDATE_SKILL = "Java";

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private JobRepository jobRepository;

	private Company company;
	private Job job;

	@BeforeEach
	void setUp() {
		companyRepository.deleteAll();
		jobRepository.deleteAll();

		company = Company.builder()
						 .name("원티드랩")
						 .nation("한국")
						 .region("서울")
						 .build();

		job = Job.builder()
				 .company(company)
				 .position("백엔드 주니어 개발자")
				 .compensation(1000000)
				 .content("원티드랩에서 백엔드 주니어 개발자를 채용합니다.")
				 .skill("Python")
				 .build();

		companyRepository.save(company);
		jobRepository.save(job);
	}

	@DisplayName("채용공고 등록 확인")
	@Test
	void postInsertTest() {
		assertThat(jobRepository.findById(job.getId())
								.isPresent()).isEqualTo(true);
	}

	@DisplayName("채용공고 수정 확인")
	@Test
	void postUpdateTest() {
		Job findJob = jobRepository.findById(job.getId())
								   .get();

		findJob.changeInfo(job.getPosition(), UPDATE_COMPENSATION, UPDATE_SKILL, job.getContent());

		Optional<Job> retrievedJob = jobRepository.findById(job.getId());
		assertThat(retrievedJob.isPresent()).isEqualTo(true);
		assertThat(retrievedJob.get()
							   .getCompensation()).isEqualTo(UPDATE_COMPENSATION);
		assertThat(retrievedJob.get()
							   .getSkill()).isEqualTo(UPDATE_SKILL);
	}

	@DisplayName("채용공고 삭제 확인")
	@Test
	void postDeleteTest() {
		Job findJob = jobRepository.findById(job.getId())
								   .get();

		jobRepository.delete(findJob);

		Optional<Job> retrievedPost = jobRepository.findById(job.getId());
		assertThat(retrievedPost.isPresent()).isEqualTo(false);
	}

	@DisplayName("모든 채용공고 목록 확인")
	@Test
	void postFindAllTest() {
		assertThat(jobRepository.findAll()
								.size()).isEqualTo(1);
	}

	@DisplayName("채용공고 상세 조회 확인")
	@Test
	void postFindByIdTest() {
		Optional<Job> retrievedPost = jobRepository.findById(job.getId());
		assertThat(retrievedPost.isPresent()).isEqualTo(true);
		assertThat(retrievedPost.get()
								.getPosition()).isEqualTo(job.getPosition());
		assertThat(retrievedPost.get()
								.getCompany()
								.getName()).isEqualTo(company.getName());
		assertThat(retrievedPost.get()
								.getContent()).isEqualTo(job.getContent());

	}

}
