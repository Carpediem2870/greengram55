package com.example.greengram33.dm.model;

import com.example.greengram33.common.Const;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class DmMsgSelDto {
    private int page;
    private int idm;
    @JsonIgnore
    private int startIdx;
    @JsonIgnore
    private int rowCount = Const.FEED_COUNT_PER_PAGE;

    public void setPage(int page) {
        this.startIdx = (page -1) * this.rowCount;
    }
}
