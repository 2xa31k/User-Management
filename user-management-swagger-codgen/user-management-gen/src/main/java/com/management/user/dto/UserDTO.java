package com.management.user.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * UserDTO
 */
@lombok.Builder @lombok.NoArgsConstructor @lombok.AllArgsConstructor

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-03-10T10:33:28.762489700+01:00[Africa/Casablanca]")
public class UserDTO  extends RepresentationModel<UserDTO>
implements Serializable{ 

  @JsonProperty("id")
  private Long id;

  @JsonProperty("firstname")
  private String firstname;

  @JsonProperty("lastname")
  private String lastname;

  @JsonProperty("phonenumber")
  private String phonenumber;

  @JsonProperty("email")
  private String email;

  @JsonProperty("salaire")
  private Long salaire;

  @JsonProperty("skills")
  @Valid
  private List<SkillDTO> skills;

  public UserDTO id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", required = false)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UserDTO firstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  /**
   * Get firstname
   * @return firstname
  */
  
  @Schema(name = "firstname", required = false)
  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public UserDTO lastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  /**
   * Get lastname
   * @return lastname
  */
  
  @Schema(name = "lastname", required = false)
  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public UserDTO phonenumber(String phonenumber) {
    this.phonenumber = phonenumber;
    return this;
  }

  /**
   * Get phonenumber
   * @return phonenumber
  */
  
  @Schema(name = "phonenumber", required = false)
  public String getPhonenumber() {
    return phonenumber;
  }

  public void setPhonenumber(String phonenumber) {
    this.phonenumber = phonenumber;
  }

  public UserDTO email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  */
  @NotNull @javax.validation.constraints.Email
  @Schema(name = "email", required = true)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserDTO salaire(Long salaire) {
    this.salaire = salaire;
    return this;
  }

  /**
   * Get salaire
   * minimum: 2000
   * @return salaire
  */
  @Min(2000L) 
  @Schema(name = "salaire", required = false)
  public Long getSalaire() {
    return salaire;
  }

  public void setSalaire(Long salaire) {
    this.salaire = salaire;
  }

  public UserDTO skills(List<SkillDTO> skills) {
    this.skills = skills;
    return this;
  }

  public UserDTO addSkillsItem(SkillDTO skillsItem) {
    if (this.skills == null) {
      this.skills = new ArrayList<>();
    }
    this.skills.add(skillsItem);
    return this;
  }

  /**
   * Get skills
   * @return skills
  */
  @Valid 
  @Schema(name = "skills", required = false)
  public List<SkillDTO> getSkills() {
    return skills;
  }

  public void setSkills(List<SkillDTO> skills) {
    this.skills = skills;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserDTO user = (UserDTO) o;
    return Objects.equals(this.id, user.id) &&
        Objects.equals(this.firstname, user.firstname) &&
        Objects.equals(this.lastname, user.lastname) &&
        Objects.equals(this.phonenumber, user.phonenumber) &&
        Objects.equals(this.email, user.email) &&
        Objects.equals(this.salaire, user.salaire) &&
        Objects.equals(this.skills, user.skills);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname, phonenumber, email, salaire, skills);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    firstname: ").append(toIndentedString(firstname)).append("\n");
    sb.append("    lastname: ").append(toIndentedString(lastname)).append("\n");
    sb.append("    phonenumber: ").append(toIndentedString(phonenumber)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    salaire: ").append(toIndentedString(salaire)).append("\n");
    sb.append("    skills: ").append(toIndentedString(skills)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

