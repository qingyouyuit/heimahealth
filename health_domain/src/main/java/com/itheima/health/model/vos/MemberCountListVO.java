package com.itheima.health.model.vos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberCountListVO implements Serializable {
    private List<String> months;
    private List<Integer> memberCount;
}
