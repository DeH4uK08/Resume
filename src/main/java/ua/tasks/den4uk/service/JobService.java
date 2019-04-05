package ua.tasks.den4uk.service;

import ua.tasks.den4uk.dao.JobDao;
import ua.tasks.den4uk.model.Job;

import java.util.List;

public class JobService implements Service<Job> {

    private JobDao jobDao = new JobDao();

    @Override
    public Job getById(int id) {
        return jobDao.getById(id);
    }

    @Override
    public List<Job> getAll() {
        return jobDao.getAll();
    }

    @Override
    public Job create(Job job) {
        return jobDao.create(job);
    }

    @Override
    public Job update(Job job) {
        return jobDao.update(job);
    }

    @Override
    public boolean delete(int id) {
        return jobDao.delete(id);
    }

}
