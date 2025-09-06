package com.clinic.model;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "doctors")
public class Doctor {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String fullName;

  @Column(nullable = false)
  private String specialty;

  @OneToOne(optional = false)
  @JoinColumn(name = "user_id", nullable = false, unique = true)
  private User user;

  @ElementCollection
  @CollectionTable(name = "doctor_available_times", joinColumns = @JoinColumn(name = "doctor_id"))
  @Column(name = "slot_time", nullable = false)
  private List<LocalTime> availableTimes;

  // getters/setters
  public Long getId(){return id;}
  public void setId(Long id){this.id=id;}
  public String getFullName(){return fullName;}
  public void setFullName(String fullName){this.fullName=fullName;}
  public String getSpecialty(){return specialty;}
  public void setSpecialty(String specialty){this.specialty=specialty;}
  public User getUser(){return user;}
  public void setUser(User user){this.user=user;}
  public List<LocalTime> getAvailableTimes(){return availableTimes;}
  public void setAvailableTimes(List<LocalTime> availableTimes){this.availableTimes=availableTimes;}
}
