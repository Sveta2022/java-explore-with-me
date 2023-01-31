package com.example.statsservice.stat.model;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViewStats {

    private String app;  //Название сервиса
    private String uri;  //URI сервиса
    private Long hits;  //Количество просмотров


}
