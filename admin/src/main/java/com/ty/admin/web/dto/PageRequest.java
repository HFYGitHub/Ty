package com.ty.admin.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequest {

    private Long page = 1L;

    private Long pageSize = 10L;

    public long getSafePage() {
        return page == null || page < 1 ? 1L : page;
    }

    public long getSafePageSize() {
        if (pageSize == null || pageSize < 1) {
            return 10L;
        }
        return Math.min(pageSize, 200L);
    }
}
