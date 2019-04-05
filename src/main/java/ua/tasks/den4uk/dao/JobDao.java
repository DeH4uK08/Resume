package ua.tasks.den4uk.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.tasks.den4uk.database.PostgreSQLConnection;
import ua.tasks.den4uk.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDao implements Dao<Job> {

    private static final Logger logger = LogManager.getLogger(JobDao.class);

    private static final Connection connection = PostgreSQLConnection.getInstance().getConnection();

    private static final String FIND_BY_ID = "SELECT * FROM user_job WHERE job_id = ?";

    private static final String FIND_ALL = "SELECT * FROM user_job ORDER BY job_id";

    private static final String INSERT = "INSERT INTO user_job (position, \"companyName\", begin, \"end\", user_id) VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE user_job SET position = ?, \"companyName\" = ?, begin = ?, \"end\" = ?, user_id = ? WHERE job_id = ?";

    private static final String DELETE = "DELETE FROM user_job WHERE job_id = ?";

    private static final String JOB_ID = "job_id";

    private static final String POSITION = "position";

    private static final String COMPANY_NAME = "companyName";

    private static final String BEGIN = "begin";

    private static final String END = "end";

    private static final String USER_ID = "user_id";

    @Override
    public Job getById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return extractJobFromResultSet(resultSet);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }

        return null;
    }

    @Override
    public List<Job> getAll() {
        List<Job> jobs = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL);

            while (resultSet.next()) {
                jobs.add(extractJobFromResultSet(resultSet));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }

        return jobs;
    }

    @Override
    public Job create(Job job) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);

            preparedStatement.setString(1, job.getPosition());
            preparedStatement.setString(2, job.getCompanyName());
            preparedStatement.setDate(3, Date.valueOf(job.getBegin()));
            preparedStatement.setDate(4, Date.valueOf(job.getEnd()));
            preparedStatement.setInt(5, job.getUserId());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }

        return job;
    }

    @Override
    public Job update(Job job) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);

            preparedStatement.setString(1, job.getPosition());
            preparedStatement.setString(2, job.getCompanyName());
            preparedStatement.setDate(3, Date.valueOf(job.getBegin()));
            preparedStatement.setDate(4, Date.valueOf(job.getEnd()));
            preparedStatement.setInt(5, job.getUserId());
            preparedStatement.setInt(6, job.getJobId());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }

        return job;
    }

    @Override
    public boolean delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();

            return true;
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }

        return false;
    }

    private Job extractJobFromResultSet(ResultSet resultSet) throws SQLException {
        Job job = new Job();

        job.setJobId(resultSet.getInt(JOB_ID));
        job.setPosition(resultSet.getString(POSITION));
        job.setCompanyName(resultSet.getString(COMPANY_NAME));
        job.setBegin(resultSet.getDate(BEGIN));
        job.setEnd(resultSet.getDate(END));
        job.setUserId(resultSet.getInt(USER_ID));

        return job;
    }

}
