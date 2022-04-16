package com.matters.moviedb.sqlite;

import org.hibernate.dialect.Dialect;

import java.sql.Types;

/**
 Dialect for Spring Data
 (Spring Data doesn't support SQLite dialect out of the box)
 */
public class SQLDialect extends Dialect {

    public SQLDialect() {
        registerColumnType(Types.INTEGER, "integer");
        registerColumnType(Types.VARCHAR, "varchar");
        registerColumnType(Types.BLOB, "blob");
        registerColumnType(Types.BINARY, "binary");

    }
 
    public boolean supportsIdentityColumns() {
        return true;
    }
 
    public boolean hasDataTypeInIdentityColumn() {
        return false;
    }
 
    public boolean supportsCurrentTimestampSelection() {
        return true;
    }
 
    public boolean isCurrentTimestampSelectStringCallable() {
        return false;
    }
 
    public String getCurrentTimestampSelectString() {
        return "select current_timestamp";
    }
 
    public boolean supportsUnionAll() {
        return true;
    }
 
    public boolean hasAlterTable() {
        return false;
    }
 
    public boolean dropConstraints() {
        return false;
    }
 
    public String getAddColumnString() {
        return "add column";
    }
 
    public String getForUpdateString() {
        return "";
    }
 
    public boolean supportsOuterJoinForUpdate() {
        return false;
    }
 
    public String getDropForeignKeyString() {
        throw new UnsupportedOperationException("No drop foreign key syntax supported by SQLiteDialect");
    }
 
    public String getAddForeignKeyConstraintString(String constraintName, String[] foreignKey, String referencedTable,
            String[] primaryKey, boolean referencesPrimaryKey) {
        throw new UnsupportedOperationException("No add foreign key syntax supported by SQLiteDialect");
    }
 
    public String getAddPrimaryKeyConstraintString(String constraintName) {
        throw new UnsupportedOperationException("No add primary key syntax supported by SQLiteDialect");
    }
 
    public boolean supportsIfExistsBeforeTableName() {
        return true;
    }
 
    public boolean supportsCascadeDelete() {
        return false;
    }
}
