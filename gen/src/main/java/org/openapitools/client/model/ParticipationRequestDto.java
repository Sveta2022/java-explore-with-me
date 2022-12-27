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
 * Заявка на участие в событии
 */
@ApiModel(description = "Заявка на участие в событии")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-12-22T21:41:43.097762+03:00[Europe/Moscow]")
public class ParticipationRequestDto {
  public static final String SERIALIZED_NAME_CREATED = "created";
  @SerializedName(SERIALIZED_NAME_CREATED)
  private String created;

  public static final String SERIALIZED_NAME_EVENT = "event";
  @SerializedName(SERIALIZED_NAME_EVENT)
  private Long event;

  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private Long id;

  public static final String SERIALIZED_NAME_REQUESTER = "requester";
  @SerializedName(SERIALIZED_NAME_REQUESTER)
  private Long requester;

  public static final String SERIALIZED_NAME_STATUS = "status";
  @SerializedName(SERIALIZED_NAME_STATUS)
  private String status;


  public ParticipationRequestDto created(String created) {
    
    this.created = created;
    return this;
  }

   /**
   * Дата и время создания заявки
   * @return created
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "2022-09-06T21:10:05.432", value = "Дата и время создания заявки")

  public String getCreated() {
    return created;
  }


  public void setCreated(String created) {
    this.created = created;
  }


  public ParticipationRequestDto event(Long event) {
    
    this.event = event;
    return this;
  }

   /**
   * Идентификатор события
   * @return event
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "1", value = "Идентификатор события")

  public Long getEvent() {
    return event;
  }


  public void setEvent(Long event) {
    this.event = event;
  }


  public ParticipationRequestDto id(Long id) {
    
    this.id = id;
    return this;
  }

   /**
   * Идентификатор заявки
   * @return id
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "3", value = "Идентификатор заявки")

  public Long getId() {
    return id;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public ParticipationRequestDto requester(Long requester) {
    
    this.requester = requester;
    return this;
  }

   /**
   * Идентификатор пользователя, отправившего заявку
   * @return requester
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "2", value = "Идентификатор пользователя, отправившего заявку")

  public Long getRequester() {
    return requester;
  }


  public void setRequester(Long requester) {
    this.requester = requester;
  }


  public ParticipationRequestDto status(String status) {
    
    this.status = status;
    return this;
  }

   /**
   * Статус заявки
   * @return status
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "PENDING", value = "Статус заявки")

  public String getStatus() {
    return status;
  }


  public void setStatus(String status) {
    this.status = status;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParticipationRequestDto participationRequestDto = (ParticipationRequestDto) o;
    return Objects.equals(this.created, participationRequestDto.created) &&
        Objects.equals(this.event, participationRequestDto.event) &&
        Objects.equals(this.id, participationRequestDto.id) &&
        Objects.equals(this.requester, participationRequestDto.requester) &&
        Objects.equals(this.status, participationRequestDto.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(created, event, id, requester, status);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParticipationRequestDto {\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    event: ").append(toIndentedString(event)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    requester: ").append(toIndentedString(requester)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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
