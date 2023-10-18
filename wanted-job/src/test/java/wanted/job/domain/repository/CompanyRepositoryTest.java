package wanted.job.domain.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import wanted.job.domain.entity.Company;

@SpringBootTest
@Transactional
class CompanyRepositoryTest {

	@Autowired
	private CompanyRepository companyRepository;

	private Company company;

	@BeforeEach
	void setUp() {
		companyRepository.deleteAll();

		company = Company.builder()
						 .name("29cm")
						 .nation("한국")
						 .region("서울")
						 .build();

		companyRepository.save(company);
	}

	@DisplayName("회사 저장 확인")
	@Test
	void userInsertTest() {
		assertThat(companyRepository.findById(company.getId())
									.isPresent()).isEqualTo(true);
	}
}