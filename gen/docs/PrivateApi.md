# PrivateApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addEvent**](PrivateApi.md#addEvent) | **POST** /users/{userId}/events | Добавление нового события
[**addParticipationRequest**](PrivateApi.md#addParticipationRequest) | **POST** /users/{userId}/requests | Добавление запроса от текущего пользователя на участие в событии
[**cancelEvent**](PrivateApi.md#cancelEvent) | **PATCH** /users/{userId}/events/{eventId} | Отмена события добавленного текущим пользователем.
[**cancelParticipationRequest**](PrivateApi.md#cancelParticipationRequest) | **PATCH** /users/{userId}/events/{eventId}/requests/{reqId}/reject | Отклонение чужой заявки на участие в событии текущего пользователя
[**cancelRequest**](PrivateApi.md#cancelRequest) | **PATCH** /users/{userId}/requests/{requestId}/cancel | Отмена своего запроса на участие в событии
[**confirmParticipationRequest**](PrivateApi.md#confirmParticipationRequest) | **PATCH** /users/{userId}/events/{eventId}/requests/{reqId}/confirm | Подтверждение чужой заявки на участие в событии текущего пользователя
[**getEvent**](PrivateApi.md#getEvent) | **GET** /users/{userId}/events/{eventId} | Получение полной информации о событии добавленном текущим пользователем
[**getEventParticipants**](PrivateApi.md#getEventParticipants) | **GET** /users/{userId}/events/{eventId}/requests | Получение информации о запросах на участие в событии текущего пользователя
[**getEvents**](PrivateApi.md#getEvents) | **GET** /users/{userId}/events | Получение событий, добавленных текущим пользователем
[**getUserRequests**](PrivateApi.md#getUserRequests) | **GET** /users/{userId}/requests | Получение информации о заявках текущего пользователя на участие в чужих событиях
[**updateEvent1**](PrivateApi.md#updateEvent1) | **PATCH** /users/{userId}/events | Изменение события добавленного текущим пользователем


<a name="addEvent"></a>
# **addEvent**
> EventFullDto addEvent(userId, newEventDto)

Добавление нового события

Обратите внимание: дата и время на которые намечено событие не может быть раньше, чем через два часа от текущего момента

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PrivateApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    PrivateApi apiInstance = new PrivateApi(defaultClient);
    Long userId = 56L; // Long | id текущего пользователя
    NewEventDto newEventDto = new NewEventDto(); // NewEventDto | данные добавляемого события
    try {
      EventFullDto result = apiInstance.addEvent(userId, newEventDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PrivateApi#addEvent");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Long**| id текущего пользователя |
 **newEventDto** | [**NewEventDto**](NewEventDto.md)| данные добавляемого события |

### Return type

[**EventFullDto**](EventFullDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | Событие добавлено |  -  |
**400** | Запрос составлен с ошибкой |  -  |
**403** | Не выполнены условия для совершения операции |  -  |
**404** | Объект не найден |  -  |
**409** | Запрос приводит к нарушению целостности данных |  -  |
**500** | Внутренняя ошибка сервера |  -  |

<a name="addParticipationRequest"></a>
# **addParticipationRequest**
> ParticipationRequestDto addParticipationRequest(userId, eventId)

Добавление запроса от текущего пользователя на участие в событии

Обратите внимание: - нельзя добавить повторный запрос - инициатор события не может добавить запрос на участие в своём событии - нельзя участвовать в неопубликованном событии - если у события достигнут лимит запросов на участие - необходимо вернуть ошибку - если для события отключена пре-модерация запросов на участие, то запрос должен автоматически перейти в состояние подтвержденного

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PrivateApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    PrivateApi apiInstance = new PrivateApi(defaultClient);
    Long userId = 56L; // Long | id текущего пользователя
    Long eventId = 56L; // Long | id события
    try {
      ParticipationRequestDto result = apiInstance.addParticipationRequest(userId, eventId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PrivateApi#addParticipationRequest");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Long**| id текущего пользователя |
 **eventId** | **Long**| id события |

### Return type

[**ParticipationRequestDto**](ParticipationRequestDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | Заявка создана |  -  |
**400** | Запрос составлен с ошибкой |  -  |
**403** | Не выполнены условия для совершения операции |  -  |
**404** | Объект не найден |  -  |
**409** | Запрос приводит к нарушению целостности данных |  -  |
**500** | Внутренняя ошибка сервера |  -  |

<a name="cancelEvent"></a>
# **cancelEvent**
> EventFullDto cancelEvent(userId, eventId)

Отмена события добавленного текущим пользователем.

Обратите внимание: Отменить можно только событие в состоянии ожидания модерации.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PrivateApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    PrivateApi apiInstance = new PrivateApi(defaultClient);
    Long userId = 56L; // Long | id текущего пользователя
    Long eventId = 56L; // Long | id отменяемого события
    try {
      EventFullDto result = apiInstance.cancelEvent(userId, eventId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PrivateApi#cancelEvent");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Long**| id текущего пользователя |
 **eventId** | **Long**| id отменяемого события |

### Return type

[**EventFullDto**](EventFullDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Событие обновлено |  -  |
**400** | Запрос составлен с ошибкой |  -  |
**403** | Не выполнены условия для совершения операции |  -  |
**404** | Объект не найден |  -  |
**409** | Запрос приводит к нарушению целостности данных |  -  |
**500** | Внутренняя ошибка сервера |  -  |

<a name="cancelParticipationRequest"></a>
# **cancelParticipationRequest**
> ParticipationRequestDto cancelParticipationRequest(userId, eventId, reqId)

Отклонение чужой заявки на участие в событии текущего пользователя

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PrivateApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    PrivateApi apiInstance = new PrivateApi(defaultClient);
    Long userId = 56L; // Long | id текущего пользователя
    Long eventId = 56L; // Long | id события текущего пользователя
    Long reqId = 56L; // Long | id заявки, которую отменяет текущий пользователь
    try {
      ParticipationRequestDto result = apiInstance.cancelParticipationRequest(userId, eventId, reqId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PrivateApi#cancelParticipationRequest");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Long**| id текущего пользователя |
 **eventId** | **Long**| id события текущего пользователя |
 **reqId** | **Long**| id заявки, которую отменяет текущий пользователь |

### Return type

[**ParticipationRequestDto**](ParticipationRequestDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Заявка отклонена |  -  |
**400** | Запрос составлен с ошибкой |  -  |
**403** | Не выполнены условия для совершения операции |  -  |
**404** | Объект не найден |  -  |
**409** | Запрос приводит к нарушению целостности данных |  -  |
**500** | Внутренняя ошибка сервера |  -  |

<a name="cancelRequest"></a>
# **cancelRequest**
> ParticipationRequestDto cancelRequest(userId, requestId)

Отмена своего запроса на участие в событии

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PrivateApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    PrivateApi apiInstance = new PrivateApi(defaultClient);
    Long userId = 56L; // Long | id текущего пользователя
    Long requestId = 56L; // Long | id запроса на участие
    try {
      ParticipationRequestDto result = apiInstance.cancelRequest(userId, requestId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PrivateApi#cancelRequest");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Long**| id текущего пользователя |
 **requestId** | **Long**| id запроса на участие |

### Return type

[**ParticipationRequestDto**](ParticipationRequestDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Заявка отменена |  -  |
**400** | Запрос составлен с ошибкой |  -  |
**403** | Не выполнены условия для совершения операции |  -  |
**404** | Объект не найден |  -  |
**409** | Запрос приводит к нарушению целостности данных |  -  |
**500** | Внутренняя ошибка сервера |  -  |

<a name="confirmParticipationRequest"></a>
# **confirmParticipationRequest**
> ParticipationRequestDto confirmParticipationRequest(userId, eventId, reqId)

Подтверждение чужой заявки на участие в событии текущего пользователя

Обратите внимание: - если для события лимит заявок равен 0 или отключена пре-модерация заявок, то подтверждение заявок не требуется - нельзя подтвердить заявку, если уже достигнут лимит по заявкам на данное событие - если при подтверждении данной заявки, лимит заявок для события исчерпан, то все неподтверждённые заявки необходимо отклонить

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PrivateApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    PrivateApi apiInstance = new PrivateApi(defaultClient);
    Long userId = 56L; // Long | id текущего пользователя
    Long eventId = 56L; // Long | id события текущего пользователя
    Long reqId = 56L; // Long | id заявки, которую подтверждает текущий пользователь
    try {
      ParticipationRequestDto result = apiInstance.confirmParticipationRequest(userId, eventId, reqId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PrivateApi#confirmParticipationRequest");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Long**| id текущего пользователя |
 **eventId** | **Long**| id события текущего пользователя |
 **reqId** | **Long**| id заявки, которую подтверждает текущий пользователь |

### Return type

[**ParticipationRequestDto**](ParticipationRequestDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Заявка подтверждена |  -  |
**400** | Запрос составлен с ошибкой |  -  |
**403** | Не выполнены условия для совершения операции |  -  |
**404** | Объект не найден |  -  |
**409** | Запрос приводит к нарушению целостности данных |  -  |
**500** | Внутренняя ошибка сервера |  -  |

<a name="getEvent"></a>
# **getEvent**
> EventFullDto getEvent(userId, eventId)

Получение полной информации о событии добавленном текущим пользователем

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PrivateApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    PrivateApi apiInstance = new PrivateApi(defaultClient);
    Long userId = 56L; // Long | id текущего пользователя
    Long eventId = 56L; // Long | id события
    try {
      EventFullDto result = apiInstance.getEvent(userId, eventId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PrivateApi#getEvent");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Long**| id текущего пользователя |
 **eventId** | **Long**| id события |

### Return type

[**EventFullDto**](EventFullDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Событие найдено |  -  |
**400** | Запрос составлен с ошибкой |  -  |
**403** | Не выполнены условия для совершения операции |  -  |
**404** | Объект не найден |  -  |
**409** | Запрос приводит к нарушению целостности данных |  -  |
**500** | Внутренняя ошибка сервера |  -  |

<a name="getEventParticipants"></a>
# **getEventParticipants**
> List&lt;ParticipationRequestDto&gt; getEventParticipants(userId, eventId)

Получение информации о запросах на участие в событии текущего пользователя

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PrivateApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    PrivateApi apiInstance = new PrivateApi(defaultClient);
    Long userId = 56L; // Long | id текущего пользователя
    Long eventId = 56L; // Long | id события
    try {
      List<ParticipationRequestDto> result = apiInstance.getEventParticipants(userId, eventId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PrivateApi#getEventParticipants");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Long**| id текущего пользователя |
 **eventId** | **Long**| id события |

### Return type

[**List&lt;ParticipationRequestDto&gt;**](ParticipationRequestDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Найдены запросы на участие |  -  |
**400** | Запрос составлен с ошибкой |  -  |
**403** | Не выполнены условия для совершения операции |  -  |
**404** | Объект не найден |  -  |
**409** | Запрос приводит к нарушению целостности данных |  -  |
**500** | Внутренняя ошибка сервера |  -  |

<a name="getEvents"></a>
# **getEvents**
> List&lt;EventShortDto&gt; getEvents(userId, from, size)

Получение событий, добавленных текущим пользователем

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PrivateApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    PrivateApi apiInstance = new PrivateApi(defaultClient);
    Long userId = 56L; // Long | id текущего пользователя
    Integer from = 0; // Integer | количество элементов, которые нужно пропустить для формирования текущего набора
    Integer size = 10; // Integer | количество элементов в наборе
    try {
      List<EventShortDto> result = apiInstance.getEvents(userId, from, size);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PrivateApi#getEvents");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Long**| id текущего пользователя |
 **from** | **Integer**| количество элементов, которые нужно пропустить для формирования текущего набора | [optional] [default to 0]
 **size** | **Integer**| количество элементов в наборе | [optional] [default to 10]

### Return type

[**List&lt;EventShortDto&gt;**](EventShortDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | События найдены |  -  |
**400** | Запрос составлен с ошибкой |  -  |
**403** | Не выполнены условия для совершения операции |  -  |
**404** | Объект не найден |  -  |
**409** | Запрос приводит к нарушению целостности данных |  -  |
**500** | Внутренняя ошибка сервера |  -  |

<a name="getUserRequests"></a>
# **getUserRequests**
> List&lt;ParticipationRequestDto&gt; getUserRequests(userId)

Получение информации о заявках текущего пользователя на участие в чужих событиях

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PrivateApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    PrivateApi apiInstance = new PrivateApi(defaultClient);
    Long userId = 56L; // Long | id текущего пользователя
    try {
      List<ParticipationRequestDto> result = apiInstance.getUserRequests(userId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PrivateApi#getUserRequests");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Long**| id текущего пользователя |

### Return type

[**List&lt;ParticipationRequestDto&gt;**](ParticipationRequestDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Найдены запросы на участие |  -  |
**400** | Запрос составлен с ошибкой |  -  |
**403** | Не выполнены условия для совершения операции |  -  |
**404** | Объект не найден |  -  |
**409** | Запрос приводит к нарушению целостности данных |  -  |
**500** | Внутренняя ошибка сервера |  -  |

<a name="updateEvent1"></a>
# **updateEvent1**
> EventFullDto updateEvent1(userId, updateEventRequest)

Изменение события добавленного текущим пользователем

Обратите внимание: - изменить можно только отмененные события или события в состоянии ожидания модерации - если редактируется отменённое событие, то оно автоматически переходит в состояние ожидания модерации - дата и время на которые намечено событие не может быть раньше, чем через два часа от текущего момента 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PrivateApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    PrivateApi apiInstance = new PrivateApi(defaultClient);
    Long userId = 56L; // Long | id текущего пользователя
    UpdateEventRequest updateEventRequest = new UpdateEventRequest(); // UpdateEventRequest | Новые данные события
    try {
      EventFullDto result = apiInstance.updateEvent1(userId, updateEventRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PrivateApi#updateEvent1");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Long**| id текущего пользователя |
 **updateEventRequest** | [**UpdateEventRequest**](UpdateEventRequest.md)| Новые данные события |

### Return type

[**EventFullDto**](EventFullDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Событие обновлено |  -  |
**400** | Запрос составлен с ошибкой |  -  |
**403** | Не выполнены условия для совершения операции |  -  |
**404** | Объект не найден |  -  |
**409** | Запрос приводит к нарушению целостности данных |  -  |
**500** | Внутренняя ошибка сервера |  -  |

