package com.bnk.ledger.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Nonnull;
import org.springframework.jdbc.core.RowMapper;

/**
 * Utility class for the data base operations.
 */
public class DbUtil {

  private static class StringRowMapper implements RowMapper<String> {

    @Override
    public String mapRow(@Nonnull ResultSet rs, int rowNum) throws SQLException {
      return rs.getString(1);
    }
  }

  public RowMapper<Long> longRowMapper() {
    return new LongRowMapper();
  }

  public RowMapper<String> stringRowMapper() {
    return new StringRowMapper();
  }

  private static class LongRowMapper implements RowMapper<Long> {

    @Override
    public Long mapRow(@Nonnull ResultSet rs, int rowNum) throws SQLException {
      return rs.getLong(1);
    }
  }

  public String commaSeparatedQuestionMarks(int count) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < count; i++) {
      sb.append("?,");
    }
    return sb.toString().substring(0, sb.toString().length() - 1);
  }
}

