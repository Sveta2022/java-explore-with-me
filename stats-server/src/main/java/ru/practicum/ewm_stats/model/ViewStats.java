package ru.practicum.ewm_stats.model;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ViewStats {

    private String app;  //Название сервиса
    private String uri;  //URI сервиса
    private Long hits;  //Количество просмотров


}
