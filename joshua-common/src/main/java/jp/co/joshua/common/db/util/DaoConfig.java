package jp.co.joshua.common.db.util;

import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.seasar.doma.jdbc.AbstractJdbcLogger;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.JdbcLogger;
import org.seasar.doma.jdbc.SqlFileRepository;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import jp.co.joshua.common.bean.ApplicationComponent;
import jp.co.joshua.common.log.Logger;
import jp.co.joshua.common.log.LoggerFactory;

/**
 * Dao設定情報クラス
 *
 * @version 1.0.0
 */
@Configuration
public class DaoConfig implements Config {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private Dialect dialect;
    @Autowired
    private SqlFileRepository sqlFileRepository;
    @Autowired
    private ApplicationComponent component;

    public DaoConfig() {
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Dialect getDialect() {
        return dialect;
    }

    public void setDialect(Dialect dialect) {
        this.dialect = dialect;
    }

    @Override
    public SqlFileRepository getSqlFileRepository() {
        return sqlFileRepository;
    }

    public void setSqlFileRepository(SqlFileRepository sqlFileRepository) {
        this.sqlFileRepository = sqlFileRepository;
    }

    @Override
    public JdbcLogger getJdbcLogger() {
        return new DaoLogger(Stream.of(Level.class.getEnumConstants())
                .filter(e -> e.toString().equals(component.getSpringframeworkJdbc()))
                .findFirst().orElse(Level.INFO));
    }

    /**
     * Daoのロガー
     *
     * @version 1.0.0
     */
    public static class DaoLogger extends AbstractJdbcLogger<Level> {

        private static final Logger LOG = LoggerFactory.getLogger(DaoLogger.class);

        public DaoLogger() {
            this(Level.DEBUG);
        }

        public DaoLogger(Level level) {
            super(level);
        }

        @Override
        public void log(Level level, String callerClassName, String callerMethodName,
                Throwable throwable, Supplier<String> messageSupplier) {

            switch (level) {
            case ERROR:
                LOG.error(messageSupplier.get(), throwable);
                break;
            case WARN:
                LOG.warn(messageSupplier.get(), throwable);
                break;
            case INFO:
                LOG.info(messageSupplier.get());
                break;
            case DEBUG:
                LOG.debug(messageSupplier.get());
                break;
            default:
                break;
            }

        }

    }

}
