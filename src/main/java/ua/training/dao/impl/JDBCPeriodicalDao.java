package ua.training.dao.impl;

import ua.training.dao.PeriodicalDao;
import ua.training.dao.mapper.ArticleMapper;
import ua.training.dao.mapper.PeriodicalMapper;
import ua.training.dao.util.ConnectionUtil;
import ua.training.model.entity.Article;
import ua.training.model.entity.Periodical;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCPeriodicalDao implements PeriodicalDao {
    private Connection connection;

    public JDBCPeriodicalDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Periodical entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO periodical " +
                "(name, description, price) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setInt(3, entity.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Periodical findById(int id) {
        Periodical periodical;
        List<Article> articles = new ArrayList<>();
        PeriodicalMapper periodicalMapper = new PeriodicalMapper();
        ArticleMapper articleMapper = new ArticleMapper();

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM project4db.periodical LEFT JOIN article USING (idperiodical) WHERE idperiodical = (?)")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            periodical = periodicalMapper.extractFromResultSet(rs);
            if (rs.getString("date_of_publication") == null) {
                periodical.setArticles(new ArrayList<>());
                return periodical;
            }
            articles.add(articleMapper.extractFromResultSet(rs));
            while (rs.next()) {
                articles.add(articleMapper.extractFromResultSet(rs));
            }
            periodical.setArticles(articles);
            return periodical;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Periodical> findAll() {
        List<Periodical> result = new ArrayList<>();
        PeriodicalMapper periodicalMapper = new PeriodicalMapper();

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM periodical");
            while (rs.next()) {
                result.add(periodicalMapper.extractFromResultSet(rs));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Periodical entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Periodical> findByUser(int userId) {
        List<Periodical> periodicals = new ArrayList<>();
        PeriodicalMapper periodicalMapper = new PeriodicalMapper();

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM project4db.periodical LEFT JOIN order_has_periodical USING (idperiodical) LEFT JOIN project4db.order USING (idorder) LEFT JOIN project4db.user USING (iduser) WHERE iduser = (?)")) {
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                periodicals.add(periodicalMapper.extractFromResultSet(rs));
            }

            return periodicals;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        ConnectionUtil.close(connection);
    }
}
