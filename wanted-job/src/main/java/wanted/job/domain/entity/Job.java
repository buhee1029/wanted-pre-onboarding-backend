package wanted.job.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "jobs")
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String position;
	private int compensation;
	private String skill;

	@Lob
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id", referencedColumnName = "id")
	private Company company;

	protected Job() {
	}

	@Builder
	private Job(Long id, String position, int compensation, String content, String skill, Company company) {
		this.id = id;
		this.position = position;
		this.compensation = compensation;
		this.content = content;
		this.skill = skill;
		this.company = company;
	}

	public void changeInfo(String position, int compensation, String skill, String content) {
		this.position = position;
		this.compensation = compensation;
		this.skill = skill;
		this.content = content;
	}
}
