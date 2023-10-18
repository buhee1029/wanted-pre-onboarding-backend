package wanted.job.domain.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "companys")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String nation;
	private String region;

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<Job> jobList = new ArrayList<>();

	protected Company() {
	}

	@Builder
	private Company(Long id, String name, String nation, String region) {
		this.id = id;
		this.name = name;
		this.nation = nation;
		this.region = region;
	}

	public void addJob(Job job) {
		this.getJobList().add(job);
	}
}
