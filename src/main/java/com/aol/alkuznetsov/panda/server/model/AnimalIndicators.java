package com.aol.alkuznetsov.panda.server.model;

import com.aol.alkuznetsov.panda.server.enums.AggressionLevel;
import com.aol.alkuznetsov.panda.server.enums.AppetiteLevel;
import com.aol.alkuznetsov.panda.server.enums.BleedingLevel;
import com.aol.alkuznetsov.panda.server.enums.ConsciousnessLevel;
import com.aol.alkuznetsov.panda.server.enums.MobilityLossLevel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Entity
@Table(name = "animal_indicators")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class AnimalIndicators {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_id_sequence")
  @SequenceGenerator(name = "global_id_sequence", allocationSize = 5)
  private Long id;

  @Column(name = "age")
  private Integer age;

  @Column(name = "is_infant")
  private Boolean isInfant;

  @Column(name = "consciousness_level")
  private ConsciousnessLevel consciousnessLevel;
  
  @Column(name = "height")
  private Double height;
  
  @Column(name = "breathing_rate")
  private Integer breathingRate;
  
  @Column(name = "heart_rate")
  private Integer heartRate;
  
  @Column(name = "bleeding_level")
  private BleedingLevel bleedingLevel;
  
  @Column(name = "body_temperature")
  private Double bodyTemperature;
  
  @Column(name = "severe_damage_count")
  private Integer severeDamageCount;
  
  @Column(name = "mild_damage_count")
  private Integer mildDamageCount;

  @Column(name = "mobility_loss_level")
  private MobilityLossLevel mobilityLossLevel;
  
  @Column(name = "appetite_level")
  private AppetiteLevel appetiteLevel;

  @Column(name = "has_symptoms")
  private Boolean hasSymptoms;
  
  @Column(name = "is_pregnant")
  private Boolean isPregnant;
  
  @Column(name = "aggression_level")
  private AggressionLevel aggressionLevel;
}
