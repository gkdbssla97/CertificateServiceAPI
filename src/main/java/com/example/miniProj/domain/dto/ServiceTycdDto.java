package com.example.miniProj.domain.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ServiceTycdDto {
    private String serviceTycd;
    private String signTargetTycd;
}
