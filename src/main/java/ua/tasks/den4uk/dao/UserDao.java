package ua.tasks.den4uk.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.tasks.den4uk.database.PostgreSQLConnection;
import ua.tasks.den4uk.model.Job;
import ua.tasks.den4uk.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao<User> {

    private static final Logger logger = LogManager.getLogger(UserDao.class);

    private static final Connection connection = PostgreSQLConnection.getInstance().getConnection();

    private static final String FIND_BY_ID = "SELECT * FROM \"user\" WHERE user_id = ?";

    private static final String FIND_JOB_BY_ID = "SELECT * FROM user_job WHERE user_id = ?";

    private static final String FIND_ALL = "SELECT * FROM \"user\" ORDER BY user_id";

    private static final String INSERT = "INSERT INTO \"user\" (\"firstName\", \"lastName\", birthday, email, \"phoneNumber\") VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE \"user\" SET \"firstName\" = ?, \"lastName\" = ?, birthday = ?, email = ?, \"phoneNumber\" = ? WHERE user_id = ?";

    private static final String DELETE = "DELETE FROM \"user\" WHERE user_id = ?";

    private static final String FIND_BY_POSITION = "SELECT * FROM \"user\" JOIN user_job ON \"user\".user_id = user_job.user_id WHERE position = ?";

    private static final String USER_ID = "user_id";

    private static final String FIRST_NAME = "firstName";

    private static final String LAST_NAME = "lastName";

    private static final String BIRTHDAY = "birthday";

    private static final String EMAIL = "email";

    private static final String PHONE_NUMBER = "phoneNumber";

    private static final String JOB_ID = "job_id";

    private static final String POSITION = "position";

    private static final String COMPANY_NAME = "companyName";

    private static final String BEGIN = "begin";

    private static final String END = "end";

    @Override
    public User getById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return extractUserFromResultSet(resultSet);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }

        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL);

            while (resultSet.next()) {
                users.add(extractUserFromResultSet(resultSet));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }

        return users;
    }

    @Override
    public User create(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setDate(3, Date.valueOf(user.getBirthday()));
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPhoneNumber());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }

        return user;
    }

    @Override
    public User update(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setDate(3, Date.valueOf(user.getBirthday()));
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPhoneNumber());
            preparedStatement.setInt(6, user.getUserId());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }

        return user;
    }

    @Override
    public boolean delete(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement(DELETE);

            ps.setInt(1, id);

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_JOB_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Job> jobs = extractJobsFromResultSet(resultSet);

            JobDao jobDao = new JobDao();
            for (Job job : jobs) {
                jobDao.delete(job.getJobId());
            }

            ps.executeUpdate();
            ps.close();
            preparedStatement.close();

            return true;
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }

        return false;
    }

    public List<User> getUsersByPosition(String position) {
        List<User> users = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_POSITION);
            preparedStatement.setString(1, position);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                users.add(extractUserFromResultSet(resultSet));
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }

        return users;
    }

    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setUserId(resultSet.getInt(USER_ID));
        user.setFirstName(resultSet.getString(FIRST_NAME));
        user.setLastName(resultSet.getString(LAST_NAME));
        user.setBirthday(resultSet.getDate(BIRTHDAY));
        user.setEmail(resultSet.getString(EMAIL));
        user.setPhoneNumber(resultSet.getString(PHONE_NUMBER));

        PreparedStatement preparedStatement = connection.prepareStatement(FIND_JOB_BY_ID);
        preparedStatement.setInt(1, resultSet.getInt(USER_ID));

        ResultSet rs = preparedStatement.executeQuery();

        user.setJobs(extractJobsFromResultSet(rs));

        return user;
    }

    private List<Job> extractJobsFromResultSet(ResultSet resultSet) throws SQLException {
        List<Job> jobs = new ArrayList<>();

        while (resultSet.next()) {
            Job job = new Job();

            job.setJobId(resultSet.getInt(JOB_ID));
            job.setPosition(resultSet.getString(POSITION));
            job.setCompanyName(resultSet.getString(COMPANY_NAME));
            job.setBegin(resultSet.getDate(BEGIN));
            job.setEnd(resultSet.getDate(END));
            job.setUserId(resultSet.getInt(USER_ID));

            jobs.add(job);
        }

        return jobs;
    }

}
