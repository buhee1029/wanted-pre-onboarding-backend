package wanted.job.Job.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class JobRequest {

	@NotNull
	private Long companyId;

	@NotBlank
	private String position;

	private int compensation;

	@NotBlank
	private String content;

	@NotBlank
	private String skill;

	public JobRequest() {
	}

	public JobRequest(
		Long companyId, String position, int compensation, String content, String skill
	) {
		this.companyId = companyId;
		this.position = position;
		this.compensation = compensation;
		this.content = content;
		this.skill = skill;
	}
}
