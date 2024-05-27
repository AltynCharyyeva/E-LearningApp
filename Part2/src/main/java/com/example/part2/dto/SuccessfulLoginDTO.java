
package com.example.part2.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SuccessfulLoginDTO {
    private Long id;
    private String role;

}
