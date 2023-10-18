package wanted.job.Job.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import wanted.job.domain.entity.Company;
import wanted.job.domain.entity.Job;

@Getter
public class JobDetailResponse {

	private Long id;
	private String companyName;
	private String companyNation;
	private String companyRegion;
	private String position;
	private int compensation;
	private String skill;
	private String content;

	private List<Long> anotherJobs;

	public JobDetailResponse(Job job, Company company) {
		this.id = job.getId();
		this.companyName = company.getName();
		this.companyNation = company.getNation();
		this.companyRegion = company.getRegion();
		this.position = job.getPosition();
		this.compensation = job.getCompensation();
		this.skill = job.getSkill();
		this.content = job.getContent();
		this.anotherJobs = company.getJobs()
								  .stream()
								  .map(Job::getId)
								  .collect(Collectors.toList());
	}
}
