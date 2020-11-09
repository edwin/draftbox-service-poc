package com.rumahbangsa.draftbox.service.posgres;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rumahbangsa.draftbox.model.TestData;
import com.rumahbangsa.draftbox.model.postgres.PhoneData;
import com.rumahbangsa.draftbox.service.MetaService;
import io.agroal.api.AgroalDataSource;
import io.quarkus.agroal.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ApplicationScoped
public class PhoneService {

    Logger logger = LoggerFactory.getLogger(MetaService.class);

    private static final String FIND_ALL = "SELECT * FROM phonebook";

    @Inject
    @DataSource("phonebook")
    AgroalDataSource phoneDataSource;

    public PhoneData findAll() {
        try (Connection connection = phoneDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new PhoneData(
                            resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
        return null;
    }

}
