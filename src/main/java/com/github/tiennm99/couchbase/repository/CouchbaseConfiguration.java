package com.github.tiennm99.couchbase.repository;

import com.couchbase.client.java.query.N1qlQuery;
import com.github.tiennm99.couchbase.model.Airline;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.couchbase.config.BeanNames;
import org.springframework.data.couchbase.core.CouchbaseOperations;
import org.springframework.data.couchbase.repository.support.IndexManager;

/**
 * Simple configuration class.
 */
@SpringBootApplication
@RequiredArgsConstructor
public class CouchbaseConfiguration {

  private final CouchbaseOperations couchbaseOperations;


  public static void main(String[] args) {
    SpringApplication.run(CouchbaseConfiguration.class, args);
  }

  /**
   * Create an {@link IndexManager} that allows index creation.
   *
   * @return
   */
  @Bean(name = BeanNames.COUCHBASE_INDEX_MANAGER)
  public IndexManager indexManager() {
    return new IndexManager(true, true, false);
  }

  @PostConstruct
  private void postConstruct() {

    // Need to post-process travel data to add _class attribute
    List<Airline> airlinesWithoutClassAttribute = couchbaseOperations.findByN1QL(
        N1qlQuery.simple( //
            "SELECT META(`travel-sample`).id AS _ID, META(`travel-sample`).cas AS _CAS, `travel-sample`.* "
                + //
                "FROM `travel-sample` " + //
                "WHERE type = \"airline\" AND _class IS MISSING;"),
        Airline.class);

    airlinesWithoutClassAttribute.forEach(couchbaseOperations::save);
  }
}
