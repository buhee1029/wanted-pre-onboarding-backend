package wanted.job.Job.dto;

import lombok.Getter;
import wanted.job.domain.entity.Company;
import wanted.job.domain.entity.Job;

@Getter
public class JobResponse {
	private Long id;
	private String companyName;
	private String companyNation;
	private String companyRegion;
	private String position;
	private int compensation;
	private String skill;

	public JobResponse(Job job, Company company) {
		this.id = job.getId();
		this.companyName = company.getName();
		this.companyNation = company.getNation();
		this.companyRegion = company.getRegion();
		this.position = job.getPosition();
		this.compensation = job.getCompensation();
		this.skill = job.getSkill();
	}
}
