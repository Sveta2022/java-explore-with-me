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
import org.openapitools.client.model.CategoryDto;
import org.openapitools.client.model.UserShortDto;

/**
 * Краткая информация о событии
 */
@ApiModel(description = "Краткая информация о событии")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-12-22T21:41:43.097762+03:00[Europe/Moscow]")
public class EventShortDto {
  public static final String SERIALIZED_NAME_ANNOTATION = "annotation";
  @SerializedName(SERIALIZED_NAME_ANNOTATION)
  private String annotation;

  public static final String SERIALIZED_NAME_CATEGORY = "category";
  @SerializedName(SERIALIZED_NAME_CATEGORY)
  private CategoryDto category;

  public static final String SERIALIZED_NAME_CONFIRMED_REQUESTS = "confirmedRequests";
  @SerializedName(SERIALIZED_NAME_CONFIRMED_REQUESTS)
  private Long confirmedRequests;

  public static final String SERIALIZED_NAME_EVENT_DATE = "eventDate";
  @SerializedName(SERIALIZED_NAME_EVENT_DATE)
  private String eventDate;

  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private Long id;

  public static final String SERIALIZED_NAME_INITIATOR = "initiator";
  @SerializedName(SERIALIZED_NAME_INITIATOR)
  private UserShortDto initiator;

  public static final String SERIALIZED_NAME_PAID = "paid";
  @SerializedName(SERIALIZED_NAME_PAID)
  private Boolean paid;

  public static final String SERIALIZED_NAME_TITLE = "title";
  @SerializedName(SERIALIZED_NAME_TITLE)
  private String title;

  public static final String SERIALIZED_NAME_VIEWS = "views";
  @SerializedName(SERIALIZED_NAME_VIEWS)
  private Long views;


  public EventShortDto annotation(String annotation) {
    
    this.annotation = annotation;
    return this;
  }

   /**
   * Краткое описание
   * @return annotation
  **/
  @ApiModelProperty(example = "Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории", required = true, value = "Краткое описание")

  public String getAnnotation() {
    return annotation;
  }


  public void setAnnotation(String annotation) {
    this.annotation = annotation;
  }


  public EventShortDto category(CategoryDto category) {
    
    this.category = category;
    return this;
  }

   /**
   * Get category
   * @return category
  **/
  @ApiModelProperty(required = true, value = "")

  public CategoryDto getCategory() {
    return category;
  }


  public void setCategory(CategoryDto category) {
    this.category = category;
  }


  public EventShortDto confirmedRequests(Long confirmedRequests) {
    
    this.confirmedRequests = confirmedRequests;
    return this;
  }

   /**
   * Количество одобренных заявок на участие в данном событии
   * @return confirmedRequests
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "5", value = "Количество одобренных заявок на участие в данном событии")

  public Long getConfirmedRequests() {
    return confirmedRequests;
  }


  public void setConfirmedRequests(Long confirmedRequests) {
    this.confirmedRequests = confirmedRequests;
  }


  public EventShortDto eventDate(String eventDate) {
    
    this.eventDate = eventDate;
    return this;
  }

   /**
   * Дата и время на которые намечено событие (в формате \&quot;yyyy-MM-dd HH:mm:ss\&quot;)
   * @return eventDate
  **/
  @ApiModelProperty(example = "2024-12-31 15:10:05", required = true, value = "Дата и время на которые намечено событие (в формате \"yyyy-MM-dd HH:mm:ss\")")

  public String getEventDate() {
    return eventDate;
  }


  public void setEventDate(String eventDate) {
    this.eventDate = eventDate;
  }


  public EventShortDto id(Long id) {
    
    this.id = id;
    return this;
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


  public void setId(Long id) {
    this.id = id;
  }


  public EventShortDto initiator(UserShortDto initiator) {
    
    this.initiator = initiator;
    return this;
  }

   /**
   * Get initiator
   * @return initiator
  **/
  @ApiModelProperty(required = true, value = "")

  public UserShortDto getInitiator() {
    return initiator;
  }


  public void setInitiator(UserShortDto initiator) {
    this.initiator = initiator;
  }


  public EventShortDto paid(Boolean paid) {
    
    this.paid = paid;
    return this;
  }

   /**
   * Нужно ли оплачивать участие
   * @return paid
  **/
  @ApiModelProperty(example = "true", required = true, value = "Нужно ли оплачивать участие")

  public Boolean getPaid() {
    return paid;
  }


  public void setPaid(Boolean paid) {
    this.paid = paid;
  }


  public EventShortDto title(String title) {
    
    this.title = title;
    return this;
  }

   /**
   * Заголовок
   * @return title
  **/
  @ApiModelProperty(example = "Знаменитое шоу 'Летающая кукуруза'", required = true, value = "Заголовок")

  public String getTitle() {
    return title;
  }


  public void setTitle(String title) {
    this.title = title;
  }


  public EventShortDto views(Long views) {
    
    this.views = views;
    return this;
  }

   /**
   * Количество просмотрев события
   * @return views
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "999", value = "Количество просмотрев события")

  public Long getViews() {
    return views;
  }


  public void setViews(Long views) {
    this.views = views;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventShortDto eventShortDto = (EventShortDto) o;
    return Objects.equals(this.annotation, eventShortDto.annotation) &&
        Objects.equals(this.category, eventShortDto.category) &&
        Objects.equals(this.confirmedRequests, eventShortDto.confirmedRequests) &&
        Objects.equals(this.eventDate, eventShortDto.eventDate) &&
        Objects.equals(this.id, eventShortDto.id) &&
        Objects.equals(this.initiator, eventShortDto.initiator) &&
        Objects.equals(this.paid, eventShortDto.paid) &&
        Objects.equals(this.title, eventShortDto.title) &&
        Objects.equals(this.views, eventShortDto.views);
  }

  @Override
  public int hashCode() {
    return Objects.hash(annotation, category, confirmedRequests, eventDate, id, initiator, paid, title, views);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EventShortDto {\n");
    sb.append("    annotation: ").append(toIndentedString(annotation)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    confirmedRequests: ").append(toIndentedString(confirmedRequests)).append("\n");
    sb.append("    eventDate: ").append(toIndentedString(eventDate)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    initiator: ").append(toIndentedString(initiator)).append("\n");
    sb.append("    paid: ").append(toIndentedString(paid)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    views: ").append(toIndentedString(views)).append("\n");
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

