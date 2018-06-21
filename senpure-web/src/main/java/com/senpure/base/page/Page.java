package com.senpure.base.page;

import java.util.List;

/**
 * Created by 罗中正 on 2017/10/12.
 */
public class Page<T> {
    private int total;
    private List<T> items;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
