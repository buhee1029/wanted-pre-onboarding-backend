package wanted.job.domain.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import wanted.job.domain.entity.User;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	private User user;

	@BeforeEach
	void setUp() {
		userRepository.deleteAll();

		user = User.builder()
				   .name("김부희")
				   .email("kimbuhui@naver.com")
				   .build();

		userRepository.save(user);
	}

	@DisplayName("사용자 저장")
	@Test
	void userInsertTest() {
		assertThat(userRepository.findById(user.getId())
								 .isPresent()).isEqualTo(true);
	}
}