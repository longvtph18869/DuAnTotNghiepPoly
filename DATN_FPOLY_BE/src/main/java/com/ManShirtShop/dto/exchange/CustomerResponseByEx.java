package com.ManShirtShop.dto.exchange;

import com.ManShirtShop.dto.role.RoleResponse;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class CustomerResponseByEx {
    private Integer id;
    private int status;
    private String email;
    private String fullname;
    private String phone;
}
