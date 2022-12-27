# AdminApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addCategory**](AdminApi.md#addCategory) | **POST** /admin/categories | Добавление новой категории
[**addEventToCompilation**](AdminApi.md#addEventToCompilation) | **PATCH** /admin/compilations/{compId}/events/{eventId} | Добавить событие в подборку
[**delete**](AdminApi.md#delete) | **DELETE** /admin/users/{userId} | Удаление пользователя
[**deleteCategory**](AdminApi.md#deleteCategory) | **DELETE** /admin/categories/{catId} | Удаление категории
[**deleteCompilation**](AdminApi.md#deleteCompilation) | **DELETE** /admin/compilations/{compId} | Удаление подборки
[**getEvents2**](AdminApi.md#getEvents2) | **GET** /admin/events | Поиск событий
[**getUsers**](AdminApi.md#getUsers) | **GET** /admin/users | Получение информации о пользователях
[**pin**](AdminApi.md#pin) | **PATCH** /admin/compilations/{compId}/pin | Закрепить подборку на главной странице
[**publishEvent**](AdminApi.md#publishEvent) | **PATCH** /admin/events/{eventId}/publish | Публикация события
[**registerUser**](AdminApi.md#registerUser) | **POST** /admin/users | Добавление нового пользователя
[**rejectEvent**](AdminApi.md#rejectEvent) | **PATCH** /admin/events/{eventId}/reject | Отклонение события
[**removeEventFromCompilation**](AdminApi.md#removeEventFromCompilation) | **DELETE** /admin/compilations/{compId}/events/{eventId} | Удалить событие из подборки
[**saveCompilation**](AdminApi.md#saveCompilation) | **POST** /admin/compilations | Добавление новой подборки
[**unpin**](AdminApi.md#unpin) | **DELETE** /admin/compilations/{compId}/pin | Открепить подборку на главной странице
[**updateCategory**](AdminApi.md#updateCategory) | **PATCH** /admin/categories | Изменение категории
[**updateEvent**](AdminApi.md#updateEvent) | **PUT** /admin/events/{eventId} | Редактирование события


<a name="addCategory"></a>
# **addCategory**
> CategoryDto addCategory(newCategoryDto)

Добавление новой категории

Обратите внимание: имя категории должно быть уникальным

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    AdminApi apiInstance = new AdminApi(defaultClient);
    NewCategoryDto newCategoryDto = new NewCategoryDto(); // NewCategoryDto | данные добавляемой категории
    try {
      CategoryDto result = apiInstance.addCategory(newCategoryDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdminApi#addCategory");
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
 **newCategoryDto** | [**NewCategoryDto**](NewCategoryDto.md)| данные добавляемой категории |

### Return type

[**CategoryDto**](CategoryDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | Категория добавлена |  -  |
**400** | Запрос составлен с ошибкой |  -  |
**403** | Не выполнены условия для совершения операции |  -  |
**404** | Объект не найден |  -  |
**409** | Запрос приводит к нарушению целостности данных |  -  |
**500** | Внутренняя ошибка сервера |  -  |

<a name="addEventToCompilation"></a>
# **addEventToCompilation**
> addEventToCompilation(compId, eventId)

Добавить событие в подборку

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    AdminApi apiInstance = new AdminApi(defaultClient);
    Long compId = 56L; // Long | id подборки
    Long eventId = 56L; // Long | id события
    try {
      apiInstance.addEventToCompilation(compId, eventId);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdminApi#addEventToCompilation");
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
 **compId** | **Long**| id подборки |
 **eventId** | **Long**| id события |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Событие добавлено |  -  |
**400** | Запрос составлен с ошибкой |  -  |
**403** | Не выполнены условия для совершения операции |  -  |
**404** | Объект не найден |  -  |
**409** | Запрос приводит к нарушению целостности данных |  -  |
**500** | Внутренняя ошибка сервера |  -  |

<a name="delete"></a>
# **delete**
> delete(userId)

Удаление пользователя

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    AdminApi apiInstance = new AdminApi(defaultClient);
    Long userId = 56L; // Long | id пользователя
    try {
      apiInstance.delete(userId);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdminApi#delete");
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
 **userId** | **Long**| id пользователя |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Пользователь удален |  -  |
**400** | Запрос составлен с ошибкой |  -  |
**403** | Не выполнены условия для совершения операции |  -  |
**404** | Объект не найден |  -  |
**409** | Запрос приводит к нарушению целостности данных |  -  |
**500** | Внутренняя ошибка сервера |  -  |

<a name="deleteCategory"></a>
# **deleteCategory**
> deleteCategory(catId)

Удаление категории

Обратите внимание: с категорией не должно быть связано ни одного события.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    AdminApi apiInstance = new AdminApi(defaultClient);
    Long catId = 56L; // Long | id категории
    try {
      apiInstance.deleteCategory(catId);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdminApi#deleteCategory");
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
 **catId** | **Long**| id категории |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Категория удалена |  -  |
**400** | Запрос составлен с ошибкой |  -  |
**403** | Не выполнены условия для совершения операции |  -  |
**404** | Объект не найден |  -  |
**409** | Запрос приводит к нарушению целостности данных |  -  |
**500** | Внутренняя ошибка сервера |  -  |

<a name="deleteCompilation"></a>
# **deleteCompilation**
> deleteCompilation(compId)

Удаление подборки

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    AdminApi apiInstance = new AdminApi(defaultClient);
    Long compId = 56L; // Long | id подборки
    try {
      apiInstance.deleteCompilation(compId);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdminApi#deleteCompilation");
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
 **compId** | **Long**| id подборки |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Подборка удалена |  -  |
**400** | Запрос составлен с ошибкой |  -  |
**403** | Не выполнены условия для совершения операции |  -  |
**404** | Объект не найден |  -  |
**409** | Запрос приводит к нарушению целостности данных |  -  |
**500** | Внутренняя ошибка сервера |  -  |

<a name="getEvents2"></a>
# **getEvents2**
> List&lt;EventFullDto&gt; getEvents2(users, states, categories, rangeStart, rangeEnd, from, size)

Поиск событий

Эндпоинт возвращает полную информацию обо всех событиях подходящих под переданные условия

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    AdminApi apiInstance = new AdminApi(defaultClient);
    List<Long> users = Arrays.asList(); // List<Long> | список id пользователей, чьи события нужно найти
    List<String> states = Arrays.asList(); // List<String> | список состояний в которых находятся искомые события
    List<Long> categories = Arrays.asList(); // List<Long> | список id категорий в которых будет вестись поиск
    String rangeStart = "rangeStart_example"; // String | дата и время не раньше которых должно произойти событие
    String rangeEnd = "rangeEnd_example"; // String | дата и время не позже которых должно произойти событие
    Integer from = 0; // Integer | количество событий, которые нужно пропустить для формирования текущего набора
    Integer size = 10; // Integer | количество событий в наборе
    try {
      List<EventFullDto> result = apiInstance.getEvents2(users, states, categories, rangeStart, rangeEnd, from, size);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdminApi#getEvents2");
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
 **users** | [**List&lt;Long&gt;**](Long.md)| список id пользователей, чьи события нужно найти | [optional]
 **states** | [**List&lt;String&gt;**](String.md)| список состояний в которых находятся искомые события | [optional]
 **categories** | [**List&lt;Long&gt;**](Long.md)| список id категорий в которых будет вестись поиск | [optional]
 **rangeStart** | **String**| дата и время не раньше которых должно произойти событие | [optional]
 **rangeEnd** | **String**| дата и время не позже которых должно произойти событие | [optional]
 **from** | **Integer**| количество событий, которые нужно пропустить для формирования текущего набора | [optional] [default to 0]
 **size** | **Integer**| количество событий в наборе | [optional] [default to 10]

### Return type

[**List&lt;EventFullDto&gt;**](EventFullDto.md)

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

<a name="getUsers"></a>
# **getUsers**
> List&lt;UserDto&gt; getUsers(ids, from, size)

Получение информации о пользователях

Возвращает информацию обо всех пользователях (учитываются параметры ограничения выборки), либо о конкретных (учитываются указанные идентификаторы)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    AdminApi apiInstance = new AdminApi(defaultClient);
    List<Long> ids = Arrays.asList(); // List<Long> | id пользователей
    Integer from = 0; // Integer | количество элементов, которые нужно пропустить для формирования текущего набора
    Integer size = 10; // Integer | количество элементов в наборе
    try {
      List<UserDto> result = apiInstance.getUsers(ids, from, size);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdminApi#getUsers");
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
 **ids** | [**List&lt;Long&gt;**](Long.md)| id пользователей | [optional]
 **from** | **Integer**| количество элементов, которые нужно пропустить для формирования текущего набора | [optional] [default to 0]
 **size** | **Integer**| количество элементов в наборе | [optional] [default to 10]

### Return type

[**List&lt;UserDto&gt;**](UserDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Пользователи найдены |  -  |
**400** | Запрос составлен с ошибкой |  -  |
**403** | Не выполнены условия для совершения операции |  -  |
**404** | Объект не найден |  -  |
**409** | Запрос приводит к нарушению целостности данных |  -  |
**500** | Внутренняя ошибка сервера |  -  |

<a name="pin"></a>
# **pin**
> pin(compId)

Закрепить подборку на главной странице

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    AdminApi apiInstance = new AdminApi(defaultClient);
    Long compId = 56L; // Long | id подборки
    try {
      apiInstance.pin(compId);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdminApi#pin");
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
 **compId** | **Long**| id подборки |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Подборка закреплена |  -  |
**400** | Запрос составлен с ошибкой |  -  |
**403** | Не выполнены условия для совершения операции |  -  |
**404** | Объект не найден |  -  |
**409** | Запрос приводит к нарушению целостности данных |  -  |
**500** | Внутренняя ошибка сервера |  -  |

<a name="publishEvent"></a>
# **publishEvent**
> EventFullDto publishEvent(eventId)

Публикация события

Обратите внимание:  - дата начала события должна быть не ранее чем за час от даты публикации. - событие должно быть в состоянии ожидания публикации

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    AdminApi apiInstance = new AdminApi(defaultClient);
    Long eventId = 56L; // Long | id события
    try {
      EventFullDto result = apiInstance.publishEvent(eventId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdminApi#publishEvent");
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
**200** | Событие опубликовано |  -  |
**400** | Запрос составлен с ошибкой |  -  |
**403** | Не выполнены условия для совершения операции |  -  |
**404** | Объект не найден |  -  |
**409** | Запрос приводит к нарушению целостности данных |  -  |
**500** | Внутренняя ошибка сервера |  -  |

<a name="registerUser"></a>
# **registerUser**
> UserDto registerUser(newUserRequest)

Добавление нового пользователя

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    AdminApi apiInstance = new AdminApi(defaultClient);
    NewUserRequest newUserRequest = new NewUserRequest(); // NewUserRequest | данные добавляемого пользователя
    try {
      UserDto result = apiInstance.registerUser(newUserRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdminApi#registerUser");
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
 **newUserRequest** | [**NewUserRequest**](NewUserRequest.md)| данные добавляемого пользователя |

### Return type

[**UserDto**](UserDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Пользователь зарегистрирован |  -  |
**400** | Запрос составлен с ошибкой |  -  |
**403** | Не выполнены условия для совершения операции |  -  |
**404** | Объект не найден |  -  |
**409** | Запрос приводит к нарушению целостности данных |  -  |
**500** | Внутренняя ошибка сервера |  -  |

<a name="rejectEvent"></a>
# **rejectEvent**
> EventFullDto rejectEvent(eventId)

Отклонение события

Обратите внимание: событие не должно быть опубликовано.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    AdminApi apiInstance = new AdminApi(defaultClient);
    Long eventId = 56L; // Long | id события
    try {
      EventFullDto result = apiInstance.rejectEvent(eventId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdminApi#rejectEvent");
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
**200** | Событие отклонено |  -  |
**400** | Запрос составлен с ошибкой |  -  |
**403** | Не выполнены условия для совершения операции |  -  |
**404** | Объект не найден |  -  |
**409** | Запрос приводит к нарушению целостности данных |  -  |
**500** | Внутренняя ошибка сервера |  -  |

<a name="removeEventFromCompilation"></a>
# **removeEventFromCompilation**
> removeEventFromCompilation(compId, eventId)

Удалить событие из подборки

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    AdminApi apiInstance = new AdminApi(defaultClient);
    Long compId = 56L; // Long | id подборки
    Long eventId = 56L; // Long | id события
    try {
      apiInstance.removeEventFromCompilation(compId, eventId);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdminApi#removeEventFromCompilation");
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
 **compId** | **Long**| id подборки |
 **eventId** | **Long**| id события |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Событие удалено из подборки |  -  |
**400** | Запрос составлен с ошибкой |  -  |
**403** | Не выполнены условия для совершения операции |  -  |
**404** | Объект не найден |  -  |
**409** | Запрос приводит к нарушению целостности данных |  -  |
**500** | Внутренняя ошибка сервера |  -  |

<a name="saveCompilation"></a>
# **saveCompilation**
> CompilationDto saveCompilation(newCompilationDto)

Добавление новой подборки

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    AdminApi apiInstance = new AdminApi(defaultClient);
    NewCompilationDto newCompilationDto = new NewCompilationDto(); // NewCompilationDto | данные новой подборки
    try {
      CompilationDto result = apiInstance.saveCompilation(newCompilationDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdminApi#saveCompilation");
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
 **newCompilationDto** | [**NewCompilationDto**](NewCompilationDto.md)| данные новой подборки |

### Return type

[**CompilationDto**](CompilationDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | Подборка добавлена |  -  |
**400** | Запрос составлен с ошибкой |  -  |
**403** | Не выполнены условия для совершения операции |  -  |
**404** | Объект не найден |  -  |
**409** | Запрос приводит к нарушению целостности данных |  -  |
**500** | Внутренняя ошибка сервера |  -  |

<a name="unpin"></a>
# **unpin**
> unpin(compId)

Открепить подборку на главной странице

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    AdminApi apiInstance = new AdminApi(defaultClient);
    Long compId = 56L; // Long | id подборки
    try {
      apiInstance.unpin(compId);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdminApi#unpin");
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
 **compId** | **Long**| id подборки |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Подборка откреплена |  -  |
**400** | Запрос составлен с ошибкой |  -  |
**403** | Не выполнены условия для совершения операции |  -  |
**404** | Объект не найден |  -  |
**409** | Запрос приводит к нарушению целостности данных |  -  |
**500** | Внутренняя ошибка сервера |  -  |

<a name="updateCategory"></a>
# **updateCategory**
> CategoryDto updateCategory(categoryDto)

Изменение категории

Обратите внимание: имя категории должно быть уникальным

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    AdminApi apiInstance = new AdminApi(defaultClient);
    CategoryDto categoryDto = new CategoryDto(); // CategoryDto | Данные категории для изменения
    try {
      CategoryDto result = apiInstance.updateCategory(categoryDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdminApi#updateCategory");
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
 **categoryDto** | [**CategoryDto**](CategoryDto.md)| Данные категории для изменения |

### Return type

[**CategoryDto**](CategoryDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Данные категории изменены |  -  |
**400** | Запрос составлен с ошибкой |  -  |
**403** | Не выполнены условия для совершения операции |  -  |
**404** | Объект не найден |  -  |
**409** | Запрос приводит к нарушению целостности данных |  -  |
**500** | Внутренняя ошибка сервера |  -  |

<a name="updateEvent"></a>
# **updateEvent**
> EventFullDto updateEvent(eventId, adminUpdateEventRequest)

Редактирование события

Редактирование данных любого события администратором. Валидация данных не требуется.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    AdminApi apiInstance = new AdminApi(defaultClient);
    Long eventId = 56L; // Long | id события
    AdminUpdateEventRequest adminUpdateEventRequest = new AdminUpdateEventRequest(); // AdminUpdateEventRequest | Данные для изменения информации о событии
    try {
      EventFullDto result = apiInstance.updateEvent(eventId, adminUpdateEventRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdminApi#updateEvent");
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
 **eventId** | **Long**| id события |
 **adminUpdateEventRequest** | [**AdminUpdateEventRequest**](AdminUpdateEventRequest.md)| Данные для изменения информации о событии |

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
**200** | Событие отредактировано |  -  |
**400** | Запрос составлен с ошибкой |  -  |
**403** | Не выполнены условия для совершения операции |  -  |
**404** | Объект не найден |  -  |
**409** | Запрос приводит к нарушению целостности данных |  -  |
**500** | Внутренняя ошибка сервера |  -  |

