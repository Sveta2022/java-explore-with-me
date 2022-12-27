/*
 * Main service API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * Пользователь
 */
@ApiModel(description = "Пользователь")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-12-22T21:41:43.097762+03:00[Europe/Moscow]")
public class UserDto {
  public static final String SERIALIZED_NAME_EMAIL = "email";
  @SerializedName(SERIALIZED_NAME_EMAIL)
  private String email;

  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private Long id;

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;


  public UserDto email(String email) {
    
    this.email = email;
    return this;
  }

   /**
   * Почтовый адрес
   * @return email
  **/
  @ApiModelProperty(example = "petrov.i@practicummail.ru", required = true, value = "Почтовый адрес")

  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }


   /**
   * Идентификатор
   * @return id
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "1", value = "Идентификатор")

  public Long getId() {
    return id;
  }




  public UserDto name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * Имя
   * @return name
  **/
  @ApiModelProperty(example = "Петров Иван", required = true, value = "Имя")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserDto userDto = (UserDto) o;
    return Objects.equals(this.email, userDto.email) &&
        Objects.equals(this.id, userDto.id) &&
        Objects.equals(this.name, userDto.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, id, name);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserDto {\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
