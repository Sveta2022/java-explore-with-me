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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.openapitools.client.model.EventShortDto;

/**
 * Подборка событий
 */
@ApiModel(description = "Подборка событий")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-12-22T21:41:43.097762+03:00[Europe/Moscow]")
public class CompilationDto {
  public static final String SERIALIZED_NAME_EVENTS = "events";
  @SerializedName(SERIALIZED_NAME_EVENTS)
  private Set<EventShortDto> events = null;

  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private Long id;

  public static final String SERIALIZED_NAME_PINNED = "pinned";
  @SerializedName(SERIALIZED_NAME_PINNED)
  private Boolean pinned;

  public static final String SERIALIZED_NAME_TITLE = "title";
  @SerializedName(SERIALIZED_NAME_TITLE)
  private String title;


  public CompilationDto events(Set<EventShortDto> events) {
    
    this.events = events;
    return this;
  }

  public CompilationDto addEventsItem(EventShortDto eventsItem) {
    if (this.events == null) {
      this.events = new LinkedHashSet<EventShortDto>();
    }
    this.events.add(eventsItem);
    return this;
  }

   /**
   * Список событий входящих в подборку
   * @return events
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "[{\"annotation\":\"Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории\",\"category\":{\"id\":1,\"name\":\"Концерты\"},\"confirmedRequests\":5,\"eventDate\":\"2024-03-10 14:30:00\",\"id\":1,\"initiator\":{\"id\":3,\"name\":\"Фёдоров Матвей\"},\"paid\":true,\"title\":\"Знаменитое шоу 'Летающая кукуруза'\",\"views\":999},{\"annotation\":\"За почти три десятилетия группа 'Java Core' закрепились на сцене как группа, объединяющая поколения.\",\"category\":{\"id\":1,\"name\":\"Концерты\"},\"confirmedRequests\":555,\"eventDate\":\"2025-09-13 21:00:00\",\"id\":1,\"initiator\":{\"id\":3,\"name\":\"Паша Петров\"},\"paid\":true,\"title\":\"Концерт рок-группы 'Java Core'\",\"views\":991}]", value = "Список событий входящих в подборку")

  public Set<EventShortDto> getEvents() {
    return events;
  }


  public void setEvents(Set<EventShortDto> events) {
    this.events = events;
  }


  public CompilationDto id(Long id) {
    
    this.id = id;
    return this;
  }

   /**
   * Идентификатор
   * @return id
  **/
  @ApiModelProperty(example = "1", required = true, value = "Идентификатор")

  public Long getId() {
    return id;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public CompilationDto pinned(Boolean pinned) {
    
    this.pinned = pinned;
    return this;
  }

   /**
   * Закреплена ли подборка на главной странице сайта
   * @return pinned
  **/
  @ApiModelProperty(example = "true", required = true, value = "Закреплена ли подборка на главной странице сайта")

  public Boolean getPinned() {
    return pinned;
  }


  public void setPinned(Boolean pinned) {
    this.pinned = pinned;
  }


  public CompilationDto title(String title) {
    
    this.title = title;
    return this;
  }

   /**
   * Заголовок подборки
   * @return title
  **/
  @ApiModelProperty(example = "Летние концерты", required = true, value = "Заголовок подборки")

  public String getTitle() {
    return title;
  }


  public void setTitle(String title) {
    this.title = title;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CompilationDto compilationDto = (CompilationDto) o;
    return Objects.equals(this.events, compilationDto.events) &&
        Objects.equals(this.id, compilationDto.id) &&
        Objects.equals(this.pinned, compilationDto.pinned) &&
        Objects.equals(this.title, compilationDto.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(events, id, pinned, title);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CompilationDto {\n");
    sb.append("    events: ").append(toIndentedString(events)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    pinned: ").append(toIndentedString(pinned)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
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

