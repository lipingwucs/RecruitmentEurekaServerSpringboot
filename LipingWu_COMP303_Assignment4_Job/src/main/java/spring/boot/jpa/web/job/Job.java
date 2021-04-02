/*COMP303_Assignment4-Job-Web
 *Liping Wu 300958061
 *4-5-2020
 * */
package spring.boot.jpa.web.job;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "job")
public class Job {   

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "jobid")
	private int jobId;	
	
	@Size(min=2, max=30)
	@Column(name = "jobcode")
	private String jobCode;
	
	@Size(min=2, max=60)
	@Column(name = "jobname")
	private String jobName;
	
	@Size(min=2,max=600)
	@Column(name = "jobdesc")
	private String jobDesc;
	
	//@PastOrPresent //pubdate should be past or present
	@NotNull(message ="publish date is mandatory")
	@Column(name = "pubdate")	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date pubDate;

	@Column(name = "numvacancy")
	@Positive(message ="number of vacancy should be above 1")
	private int numVacancy;

	// constructor with fields
	public Job(int jobId, String jobCode, String jobName, String jobDesc, String pubDate, int numVacancy) {
		super();
		System.out.println("come to this convert string to date: " + pubDate);
		this.jobId = jobId;
		this.jobCode = jobCode;
		this.jobName = jobName;
		this.jobDesc = jobDesc;
		Date startDate = new Date(0);
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd").parse(pubDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.pubDate = startDate;
		this.numVacancy = numVacancy;
	}

	// constructor with fields
	public Job(int jobId, String jobCode, String jobName, String jobDesc, Date pubDate, int numVacancy) {
		super();
		System.out.println("no convert string to date: " + pubDate);
		this.jobId = jobId;
		this.jobCode = jobCode;
		this.jobName = jobName;
		this.jobDesc = jobDesc;
		this.pubDate = pubDate;
		this.numVacancy = numVacancy;

	}

	// constructor without fields
	public Job() {
		super();
	}

	// getters and setters
	public int getJobId() {
		return jobId;
	}

	public String getJobCode() {
		return jobCode;
	}

	public String getJobName() {
		return jobName;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public int getNumVacancy() {
		return numVacancy;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public void setNumVacancy(int numVacancy) {
		this.numVacancy = numVacancy;
	}

	// toString()
	@Override
	public String toString() {
		return "Job [jobId=" + jobId + ", jobCode=" + jobCode + ", jobName=" + jobName + ", jobDesc=" + jobDesc
				+ ", pubDate=" + pubDate + ", numVacancy=" + numVacancy + "]";
	}

}
