package com.management.user.dto;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;

import org.springframework.hateoas.PagedModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * PageResponseDTO
 */
@lombok.Builder @lombok.NoArgsConstructor @lombok.AllArgsConstructor

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-03-10T15:03:34.245767800+01:00[Africa/Casablanca]")
public class PageResponseDTO   {

  @JsonProperty("users")
  private PagedModel<UserDTO> users = null;

  @JsonProperty("totalSalaire")
  private Long totalSalaire;

  @JsonProperty("averageSalaire")
  private Long averageSalaire;

  public PageResponseDTO users(PagedModel users) {
    this.users = users;
    return this;
  }

  /**
   * Get users
   * @return users
  */
  @Valid 
  @Schema(name = "users", required = false)
  public PagedModel getUsers() {
    return users;
  }

  public void setUsers(PagedModel users) {
    this.users = users;
  }

  public PageResponseDTO totalSalaire(Long totalSalaire) {
    this.totalSalaire = totalSalaire;
    return this;
  }

  /**
   * Get totalSalaire
   * @return totalSalaire
  */
  
  @Schema(name = "totalSalaire", required = false)
  public Long getTotalSalaire() {
    return totalSalaire;
  }

  public void setTotalSalaire(Long totalSalaire) {
    this.totalSalaire = totalSalaire;
  }

  public PageResponseDTO averageSalaire(Long averageSalaire) {
    this.averageSalaire = averageSalaire;
    return this;
  }

  /**
   * Get averageSalaire
   * @return averageSalaire
  */
  
  @Schema(name = "averageSalaire", required = false)
  public Long getAverageSalaire() {
    return averageSalaire;
  }

  public void setAverageSalaire(Long averageSalaire) {
    this.averageSalaire = averageSalaire;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PageResponseDTO pageResponse = (PageResponseDTO) o;
    return Objects.equals(this.users, pageResponse.users) &&
        Objects.equals(this.totalSalaire, pageResponse.totalSalaire) &&
        Objects.equals(this.averageSalaire, pageResponse.averageSalaire);
  }

  @Override
  public int hashCode() {
    return Objects.hash(users, totalSalaire, averageSalaire);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PageResponseDTO {\n");
    sb.append("    users: ").append(toIndentedString(users)).append("\n");
    sb.append("    totalSalaire: ").append(toIndentedString(totalSalaire)).append("\n");
    sb.append("    averageSalaire: ").append(toIndentedString(averageSalaire)).append("\n");
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

