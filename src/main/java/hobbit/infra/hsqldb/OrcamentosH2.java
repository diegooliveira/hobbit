package hobbit.infra.hsqldb;

import hobbit.model.pedido.Orcamento;
import hobbit.model.pedido.Orcamentos;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.flyway.core.Flyway;

public class OrcamentosH2 implements Orcamentos {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrcamentosH2.class);

    private BasicDataSource ds;

    public OrcamentosH2(Path workPath) throws IOException {

        Path dbPath = workPath.resolve("db");

        ds = new BasicDataSource();
        ds.setUrl("jdbc:hsqldb:file:" + dbPath.toString());
        ds.setUsername("SA");
        ds.setPassword("");

        Flyway flyway = new Flyway();
        flyway.setDataSource(ds);
        flyway.setLocations("hobbit.h2");
        flyway.migrate();
    }

    @Override
    public Orcamento porId(int id) {

        if (LOGGER.isDebugEnabled())
            LOGGER.debug("OrcamentosH2.porId id={}", id);

        try (Connection con = ds.getConnection()) {
            PreparedStatement prepareStatement = con.prepareStatement(SELECT_BY_ID);
            prepareStatement.setInt(1, id);
            ResultSet rs = prepareStatement.executeQuery();
            if (rs.next())
                return Orcamento.recarregar(rs.getInt(1), new Date(rs.getTimestamp(2).getTime()));
            else
                throw new RuntimeException("Id não encontrado. id=" + id);

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar orçamento", ex);
        }

    }

    @Override
    public void save(Orcamento orcamento) {
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("OrcamentosH2.save orcamento={}", orcamento);

        try (Connection con = ds.getConnection()) {
            PreparedStatement prepareStatement = con.prepareStatement(INSERT_ORCAMENTO);
            prepareStatement.setInt(1, orcamento.getId());
            prepareStatement.setTimestamp(2, new Timestamp(orcamento.getDataCriacao().getTime()));
            prepareStatement.executeUpdate();

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar orçamento", ex);
        }
    }

    @Override
    public int nextId() {
        try (Connection con = ds.getConnection()) {

            PreparedStatement prepareStatement = con.prepareStatement(SELECT_NEXT_ID);
            ResultSet executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                return executeQuery.getInt(1);
            }

            throw new RuntimeException("Deveria ter vindo um novo ID");
        } catch (SQLException e) {
            throw new RuntimeException("Deveria ter vindo um novo ID", e);
        }
    }

    private static final String SELECT_BY_ID = "SELECT ORCAMENTO_ID, ORCAMENTO_DATA FROM ORCAMENTO WHERE ORCAMENTO_ID = ?";
    private static final String SELECT_NEXT_ID = "call next value for PUBLIC.ORCAMENTO_SEQUENCE";
    private static final String INSERT_ORCAMENTO = "INSERT INTO ORCAMENTO (ORCAMENTO_ID, ORCAMENTO_DATA) VALUES (?, ?)";

}
