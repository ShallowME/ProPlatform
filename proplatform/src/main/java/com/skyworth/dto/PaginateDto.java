package com.skyworth.dto;

import com.github.pagehelper.Page;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Shallow on 2018/5/12.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PaginateDto<T>{
    private static final long serialVersionUID = 1L;
    private Long total;
    private List<T> resultList;
    private int pageNum;
    private int pageSize;
    private int pages;
    private int size;

    public PaginateDto(List<T> resultList) {
        if (resultList instanceof Page){
            Page<T> page = (Page<T>)resultList;
            this.total = page.getTotal();
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.pages = page.getPages();
            this.resultList = page;
            this.size = page.size();
        }
    }
}
