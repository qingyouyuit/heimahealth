package com.itheima.health.model.vos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberCountVO implements Serializable {
    private String months;
    private Integer memberCount;
}
