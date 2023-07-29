package com.itheima.health.model.vos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersettingVO implements Serializable {
    private Long date;
    private Integer number;
    private Integer reservations;
}
