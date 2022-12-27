package com.example.statsservice.stat.model;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class View {

    private String app;
    private String uri;
    private Long hits;
}
