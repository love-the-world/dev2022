package com.fc.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataVo<T> {
    private Long total;
    private List list;
    private Integer pageNum;
    private Integer pageSize;
}
