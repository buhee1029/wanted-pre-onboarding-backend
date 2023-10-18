package wanted.job.Job.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wanted.job.Job.dto.JobDetailResponse;
import wanted.job.Job.dto.JobRequest;
import wanted.job.Job.dto.JobResponse;
import wanted.job.Job.service.JobService;
import wanted.job.common.response.ApiResponse;

@RestController
@RequestMapping("/jobs")
public class JobController {

	private final JobService jobService;

	public JobController(JobService jobService) {
		this.jobService = jobService;
	}

	@PostMapping
	public ApiResponse<Long> registerJob(@RequestBody @Valid JobRequest jobRequest) {
		return ApiResponse.toResponse(jobService.registerJob(jobRequest));
	}

	@PutMapping("/{id}")
	public ApiResponse<Long> modifyJob(
		@PathVariable Long id,
		@RequestBody @Valid JobRequest jobRequest
	) {
		return ApiResponse.toResponse(jobService.modifyJob(id, jobRequest));
	}

	@DeleteMapping("/{id}")
	public ApiResponse<Void> removeJob(@PathVariable Long id) {
		jobService.removeJob(id);
		return ApiResponse.ok();
	}

	@GetMapping
	public ApiResponse<List<JobResponse>> getJobs() {
		return ApiResponse.toResponse(jobService.getJobs());
	}

	@GetMapping("/{id}")
	public ApiResponse<JobDetailResponse> getJob(@PathVariable Long id) {
		return ApiResponse.toResponse(jobService.getJobDetail(id));
	}

	@GetMapping("/search")
	public ApiResponse<List<JobResponse>> searchJobs(@RequestParam(required = false) String keyword) {
		return ApiResponse.toResponse(jobService.searchJobs(keyword));
	}
}
