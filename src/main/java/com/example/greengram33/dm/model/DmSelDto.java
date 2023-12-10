package com.example.greengram33.dm.model;

import com.example.greengram33.common.Const;
import lombok.Data;

@Data
public class DmSelDto {
    private int loginedIuser;
    private int page;

    private int startIdx;

    private int rowCount = Const.DM_COUNT_PER_PAGE;

    public void setPage(int page) {
        this.startIdx = (page - 1) * this.rowCount;
    }
}
