package ua.tasks.den4uk.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Job {

    private int jobId;

    private String position;
    private String companyName;

    private Date begin;
    private Date end;
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);

    private int userId;

    public Job() {

    }

    public Job(String position, String companyName, Date begin, Date end, int userId) {
        this.position = position;
        this.companyName = companyName;
        this.begin = begin;
        this.end = end;
        this.userId = userId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBegin() {
        return simpleDateFormat.format(begin);
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return simpleDateFormat.format(end);
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return jobId == job.jobId &&
                userId == job.userId &&
                Objects.equals(position, job.position) &&
                Objects.equals(companyName, job.companyName) &&
                getBegin().equals(job.getBegin()) &&
                getEnd().equals(job.getEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId, position, companyName, begin, end, userId);
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", position='" + position + '\'' +
                ", companyName='" + companyName + '\'' +
                ", begin=" + begin +
                ", end=" + end +
                ", userId=" + userId +
                '}';
    }

}
