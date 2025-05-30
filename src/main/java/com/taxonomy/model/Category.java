package com.taxonomy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne
  @JoinColumn(name = "parent_id")
  @JsonIgnoreProperties({"children"})
  private Category parentCategory;

  @JsonIgnoreProperties({"parentCategory", "children"})
  @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
  private List<Category> children = new ArrayList<>();
}
