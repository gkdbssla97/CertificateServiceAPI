package com.example.miniProj.domain.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CertificationResponseDto {
    private String reqTxId;
    private String certTxId;
}
