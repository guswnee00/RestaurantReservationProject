package org.zerobase.restaurantreservationproject.global.auth.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginInput {
    private String userName;
    private String password;
}
