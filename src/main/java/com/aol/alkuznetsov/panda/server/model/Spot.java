package com.aol.alkuznetsov.panda.server.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.geolatte.geom.C2D;
import org.geolatte.geom.Point;

@Entity
@Table(name = "spot")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Spot {

  @Id
  @Column(name = "id")
  @GeneratedValue
  private UUID id;

  @Column(name = "point")
  private Point<C2D> point;
}
