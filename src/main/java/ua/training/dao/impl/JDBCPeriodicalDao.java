package ua.training.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.dao.PeriodicalDao;
import ua.training.dao.mapper.ArticleMapper;
import ua.training.dao.mapper.PeriodicalMapper;
import ua.training.dao.util.ConnectionUtil;
import ua.training.model.entity.Article;
import ua.training.model.entity.Periodical;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCPeriodicalDao implements PeriodicalDao {
    private Connection connection;
    private static final Logger logger = LogManager.getLogger(JDBCPeriodicalDao.class);

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
            logger.info(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Periodical findById(int id) {
        Periodical periodical;
        List<Article> articles = new ArrayList<>();
        PeriodicalMapper periodicalMapper = new PeriodicalMapper();
        ArticleMapper articleMapper = new ArticleMapper();

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM project4db.periodical LEFT JOIN article USING (idperiodical) WHERE idperiodical = (?) ORDER BY date_of_publication DESC")) {
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
            logger.info(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Periodical> findAll() {
        Map<Integer, Periodical> periodicalMap = new HashMap<>();
        Map<Integer, Article> articleMap = new HashMap();
        PeriodicalMapper periodicalMapper = new PeriodicalMapper();
        ArticleMapper articleMapper = new ArticleMapper();

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM periodical LEFT JOIN article USING (idperiodical)");
            while (rs.next()) {
                Article article = null;
                Periodical periodical = periodicalMapper.extractFromResultSet(rs);
                periodical = periodicalMapper.makeUnique(periodicalMap, periodical);
                if (rs.getString("date_of_publication") != null) {
                    article = articleMapper.extractFromResultSet(rs);
                    article = articleMapper.makeUnique(articleMap, article);
                }


                periodical.getArticles().add(article);
            }
            return new ArrayList<>(periodicalMap.values());
        } catch (SQLException e) {
            logger.info(e);
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
        Map<Integer, Periodical> periodicalMap = new HashMap<>();
        Map<Integer, Article> articleMap = new HashMap();
        PeriodicalMapper periodicalMapper = new PeriodicalMapper();
        ArticleMapper articleMapper = new ArticleMapper();

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM project4db.periodical LEFT JOIN user_has_periodical USING (idperiodical) LEFT JOIN project4db.user USING (iduser) LEFT JOIN article USING (idperiodical) WHERE iduser = (?)")) {
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Article article = null;
                Periodical periodical = periodicalMapper.extractFromResultSet(rs);
                periodical = periodicalMapper.makeUnique(periodicalMap, periodical);
                if (rs.getString("date_of_publication") != null) {
                    article = articleMapper.extractFromResultSet(rs);
                    article = articleMapper.makeUnique(articleMap, article);
                }
                periodical.getArticles().add(article);
            }

            return new ArrayList<>(periodicalMap.values());
        } catch (SQLException e) {
            logger.info(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Periodical> findFixedNumberOfPeriodicals(int limit, int offset) {
        Map<Integer, Periodical> periodicalMap = new HashMap<>();
        Map<Integer, Article> articleMap = new HashMap();
        PeriodicalMapper periodicalMapper = new PeriodicalMapper();
        ArticleMapper articleMapper = new ArticleMapper();

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM (SELECT * FROM periodical LIMIT ? OFFSET ?) AS periodicals_page LEFT JOIN article USING (idperiodical)")) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Article article = null;
                Periodical periodical = periodicalMapper.extractFromResultSet(rs);
                periodical = periodicalMapper.makeUnique(periodicalMap, periodical);
                if (rs.getString("date_of_publication") != null) {
                    article = articleMapper.extractFromResultSet(rs);
                    article = articleMapper.makeUnique(articleMap, article);
                }
                periodical.getArticles().add(article);
            }

            return new ArrayList<>(periodicalMap.values());
        } catch (SQLException e) {
            logger.info(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        ConnectionUtil.close(connection);
    }
}
