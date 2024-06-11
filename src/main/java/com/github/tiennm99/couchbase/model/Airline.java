package com.github.tiennm99.couchbase.model;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

public class Airline {

  @Id
  private String id;

  @Field
  private String type;

  @Field
  private String name;

  @Field("iata")
  private String iataCode;

  @Field
  private String icao;

  @Field
  private String callsign;

  @Field
  private String country;
}
