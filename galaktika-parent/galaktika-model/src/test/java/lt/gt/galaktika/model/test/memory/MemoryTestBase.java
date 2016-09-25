package lt.gt.galaktika.model.test.memory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.jdbc.Work;
import org.junit.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import lt.gt.galaktika.model.dao.IDAO;

public class MemoryTestBase {
	final static Logger LOG = LoggerFactory.getLogger(MemoryTestBase.class);
	
	@Autowired
	@Qualifier("dao")
	IDAO dao;
	
	public void clearDatabase(IDAO dao) throws Exception {
		dao.work(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				Statement stmt = connection.createStatement();
				try {
					stmt.execute("TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK");
					connection.commit();
				} finally {
					stmt.close();
				}

			}
		});
	}
	
	@After
	public void tearDown() {
		try {
			clearDatabase(dao);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

}
