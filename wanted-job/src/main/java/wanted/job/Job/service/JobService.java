package wanted.job.Job.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.job.Job.dto.JobDetailResponse;
import wanted.job.Job.dto.JobRequest;
import wanted.job.Job.dto.JobResponse;
import wanted.job.domain.entity.Company;
import wanted.job.domain.entity.Job;
import wanted.job.domain.repository.CompanyRepository;
import wanted.job.domain.repository.JobRepository;

@Service
public class JobService {

	private final CompanyRepository companyRepository;
	private final JobRepository jobRepository;

	public JobService(CompanyRepository companyRepository, JobRepository jobRepository) {
		this.companyRepository = companyRepository;
		this.jobRepository = jobRepository;
	}

	@Transactional
	public Long registerJob(JobRequest jobRequest) {
		Company company = findCompany(jobRequest.getCompanyId());

		Job job = Job.builder()
					 .position(jobRequest.getPosition())
					 .compensation(jobRequest.getCompensation())
					 .skill(jobRequest.getSkill())
					 .content(jobRequest.getContent())
					 .company(company)
					 .build();

		company.addJob(job);

		return job.getId();
	}

	@Transactional
	public Long modifyJob(Long jobId, JobRequest jobRequest) {
		Job job = findJob(jobId);
		job.changeInfo(
			jobRequest.getPosition(), jobRequest.getCompensation(),
			jobRequest.getSkill(), jobRequest.getContent()
		);

		jobRepository.save(job);

		return job.getId();
	}

	@Transactional
	public void removeJob(Long jobId) {
		Job job = findJob(jobId);
		jobRepository.delete(job);
	}

	@Transactional(readOnly = true)
	public List<JobResponse> getJobs() {
		return jobRepository.findAll()
							.stream()
							.map(
								job -> new JobResponse(job, findCompany(job.getCompany().getId())))
							.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public JobDetailResponse getJobDetail(Long jobId) {
		Job job = findJob(jobId);

		return new JobDetailResponse(job, findCompany(job.getCompany().getId()));
	}

	@Transactional(readOnly = true)
	public List<JobResponse> searchJobs(String keyword) {
		return jobRepository.findAllByKeyword(keyword)
							.stream()
							.map(
								job -> new JobResponse(job, findCompany(job.getCompany().getId())))
							.collect(Collectors.toList());
	}

	private Company findCompany(Long id) {
		return companyRepository.findById(id)
								.get();
	}

	private Job findJob(Long id) {
		return jobRepository.findById(id)
							.get();
	}
}
