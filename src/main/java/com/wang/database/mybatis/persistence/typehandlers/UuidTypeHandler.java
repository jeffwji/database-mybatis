package com.wang.database.mybatis.persistence.typehandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class UuidTypeHandler extends BaseTypeHandler<UUID> {
	@Override
	public UUID getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return UUID.fromString(rs.getString(columnName));
	}

	@Override
	public UUID getNullableResult(ResultSet rs, int index) throws SQLException {
		return UUID.fromString(rs.getString(index));
	}

	@Override
	public UUID getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return UUID.fromString((cs.getString(columnIndex)));
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, UUID parameter, JdbcType arg3) throws SQLException {
		ps.setString(i, ((UUID) parameter).toString());
	}
}
