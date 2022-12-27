

# AdminUpdateEventRequest

Информация для редактирования события администратором. Все поля необязательные. Значение полей не валидируется.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**annotation** | **String** | Краткое описание события |  [optional]
**category** | **Long** | id категории к которой относится событие |  [optional]
**description** | **String** | Полное описание события |  [optional]
**eventDate** | **String** | Дата и время на которые намечено событие (в формате \&quot;yyyy-MM-dd HH:mm:ss\&quot;) |  [optional]
**location** | [**Location**](Location.md) |  |  [optional]
**paid** | **Boolean** | Нужно ли оплачивать участие в событии |  [optional]
**participantLimit** | **Integer** | Ограничение на количество участников. Значение 0 - означает отсутствие ограничения |  [optional]
**requestModeration** | **Boolean** | Нужна ли пре-модерация заявок на участие |  [optional]
**title** | **String** | Заголовок события |  [optional]



