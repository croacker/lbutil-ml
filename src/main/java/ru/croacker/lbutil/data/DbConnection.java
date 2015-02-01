package ru.croacker.lbutil.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 *
 */
@Entity
@Table(name="db_connection")
public class DbConnection {

  @Id
  @GeneratedValue
  @Getter @Setter
  private long id;

  @Basic
  @Column
  @Getter @Setter
  private String name;

  @Basic
  @Column
  @Getter @Setter
  private String jdbcDriver;

  @Basic
  @Column
  @Getter @Setter
  private String url;

  @Basic
  @Column
  @Getter @Setter
  private String user;

  @Basic
  @Column
  @Getter @Setter
  private String password;
}
