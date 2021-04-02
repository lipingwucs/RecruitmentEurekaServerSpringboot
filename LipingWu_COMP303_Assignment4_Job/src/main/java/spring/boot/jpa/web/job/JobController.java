/*COMP303_Assignment4-Job-Web
 *Liping Wu 300958061
 *4-5-2020
 * */

package spring.boot.jpa.web.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JobController {

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private HttpSession session;

	@InitBinder
	private void dateBinder(WebDataBinder binder) {
		// The date format to parse or output your dates
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// Create a new CustomDateEditor
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		// Register it as custom editor for the Date type
		binder.registerCustomEditor(Date.class, editor);
	}

	@RequestMapping("/jobs_list.html") // frontend url
	public String show(Model model) {
		List<Job> jobs = jobRepository.findAll();
		System.out.println("got current job list: " + jobs);
		model.addAttribute("jobList", jobs);
		return "jobs/jobList";
	}

	// Read
	@GetMapping(value = "/jobs_details.html", params = "jobId") // frontend url
	public String getJob(@RequestParam("jobId") int jobId, Model model) throws Exception {
		model.addAttribute("job", jobRepository.findById(jobId).get());
		return "jobs/jobDetails";
	}

	@GetMapping(value = "/jobs_add.html") // frontend url
	public String createJob(Model model) throws Exception {
		model.addAttribute("job", new Job());
		return "jobs/jobAdd";
	}

	// Create
	@PostMapping("/jobs_add.html")
	public String createJobPost(@Valid Job job, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "jobs/jobAdd";
		}
		jobRepository.save(job);
		session.setAttribute("tempMsg", "Create the job  " + job.getJobName() + " successfully");
		return "redirect:/jobs_list.html";
	}

	// Update
	@GetMapping(value = "/jobs_update.html", params = "jobId") // frontend url
	public String updateJob(@RequestParam("jobId") int jobId, Model model) throws Exception {
		model.addAttribute("job", jobRepository.findById(jobId));
		return "jobs/jobUpdate";
	}

	// Update
	@PostMapping("/jobs_update.html")
	public String updateJobPost(@Valid Job job, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "jobs/jobUpdate";
		}
		jobRepository.save(job);
		session.setAttribute("tempMsg", "Update the job  " + job.getJobName() + " successfully");
		return "redirect:/jobs_list.html";
	}

	// Delete
	@RequestMapping(value = "/jobs_delete.html", method = RequestMethod.GET)
	public String deleteJob(@RequestParam("jobId") int jobId, Model model) throws Exception {
		Job job = jobRepository.findById(jobId).get();
		model.addAttribute("job", job);
		String message = "Are you sure you want to delete this job " + job.getJobName() + " ?";
		model.addAttribute("confirmMsg", message);
		return "jobs/jobDelete";
	}

	// DELETE post
	@RequestMapping(value = "/jobs_delete.html", method = RequestMethod.POST)
	public String deleteJobPost(@RequestParam("jobId") int jobId, Model model) {
		Job job = jobRepository.findById(jobId).get();
		String jobName = job.getJobName();
		jobRepository.deleteById(jobId);

		session.setAttribute("tempMsg", "Deleted the job  " + jobName + " successfully");
		return "redirect:/jobs_list.html";
	}

}