package com.github.tiennm99.couchbase.repository;

import com.github.tiennm99.couchbase.model.Airline;
import java.util.List;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.View;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.repository.CrudRepository;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "airlines")
public interface AirlineRepository extends CrudRepository<Airline, String> {

  /**
   * Derived query selecting by {@code iataCode}.
   *
   * @param code
   * @return
   */
  Airline findAirlineByIataCode(String code);

  /**
   * Query method using {@code airlines/all} view.
   *
   * @return
   */
  @View(designDocument = "airlines", viewName = "all")
  List<Airline> findAllBy();
}
