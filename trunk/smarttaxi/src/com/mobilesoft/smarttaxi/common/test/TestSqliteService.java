
package com.mobilesoft.smarttaxi.common.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import com.mobilesoft.smarttaxi.common.service.BaseService;

public class TestSqliteService extends BaseService {

    private boolean isTableExist() {
        try {
            List list = getJdbcTemplate().queryForList("select * from people");
        } catch (DataAccessException ex) {
            logger.info("Table<people> does not exist!");
            return false;
        }
        return true;
    }

    public void createTable() {
        if (!isTableExist()) {
            getJdbcTemplate().update("drop table if exists people;");
            getJdbcTemplate().update("create table people (name, occupation);");
        }
    }

    public void insertTable(String name, String occupation) {
        getJdbcTemplate().update("insert into people(name, occupation) values (?, ?);",
            new Object[] { name, occupation });
    }

    public List<People> getAllPeople() {
        return (List<People>) getJdbcTemplate().query("select * from people", new RowMapper() {

            @Override
            public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
                People people = new People();
                people.setName(arg0.getString("name"));
                people.setOccupation(arg0.getString("occupation"));
                return people;
            }

        });
    }

    class People {
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOccupation() {
            return occupation;
        }

        public void setOccupation(String occupation) {
            this.occupation = occupation;
        }
        String occupation;

        public People() {
        }

        public People(String name, String occupation) {
            this.name = name;
            this.occupation = occupation;
        }
    }

}
