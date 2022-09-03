package com.rms.assignment;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;

import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import com.rms.assignment.core.entity.BaseEntity;
import com.rms.assignment.core.entity.Recipe;
import com.rms.assignment.core.entity.User;
import com.rms.assignment.spring.config.CoreTestConfiguration;

@ContextConfiguration(classes = { CoreTestConfiguration.class })
public abstract class AbstractIntegrationTest {
    protected static DbSetupTracker dbSetupTracker = new DbSetupTracker();
    private static final Operation DELETE_ALL = deleteAllFrom(User.TABLE_NAME_FAV_RECIPES, User.TABLE_NAME_USER,
            Recipe.TABLE_NAME_RECIPE);
    public static final Operation INSERT_REFERENCE_DATA = sequenceOf(DELETE_ALL,
            insertInto(User.TABLE_NAME_USER).columns("id", "username", "version").values(1000L, "masterchef", 1)
                    .build(),
            insertInto(User.TABLE_NAME_USER).columns("id", "username", "version").values(1001L, "souschef", 1).build(),
            insertInto(Recipe.TABLE_NAME_RECIPE)
                    .columns("id", "name", "description", "veg", "serves", "instructions", "ingredients", "version")
                    .values(1000L, "Oreo Ice Cream Dessert", "Oreo dessert", 1, 10,
                            "    Soften ice cream until you can mix it with other ingredients \r\n"
                                    + "    Crumble Oreo cookies\r\n"
                                    + "    Mix Oreo cookies and cool whip into ice cream\r\n" + "    Blend well\r\n"
                                    + "    Put into a flat freezer container\r\n" + "    Freeze until ready to serve",
                            "    ½ gallon vanilla ice cream\r\n" + "    1 pack Oreo cookies\r\n"
                                    + "    8 oz. Cool whip\r\n" + "",
                            10)
                    .build(),
            insertInto(User.TABLE_NAME_FAV_RECIPES).columns("user_id", "recipe_id").values(1001L, 1000L).build());

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private DataSource dataSource;

    protected void save(BaseEntity... entities) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Stream.of(entities).forEach(em::persist);
        em.getTransaction().commit();
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    @BeforeEach
    public void prepare() {
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), INSERT_REFERENCE_DATA);
        dbSetupTracker.launchIfNecessary(dbSetup);
    }

}