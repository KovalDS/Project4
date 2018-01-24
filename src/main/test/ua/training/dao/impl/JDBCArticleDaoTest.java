package ua.training.dao.impl;

import org.junit.Test;
import ua.training.dao.ArticleDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class JDBCArticleDaoTest {
    @Test
    public void findAll() throws Exception {
        Connection connection = mock(Connection.class);
        Statement statement = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);

        ArticleDao articleDao = new JDBCArticleDao(connection);
        articleDao.findAll();
        verify(connection, times(1)).createStatement();
        verify(statement, times(1)).executeQuery(anyString());
    }
}