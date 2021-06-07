package com.misnadqasim.mydaily.component;

import android.graphics.Color;

public class Task {

    public int ID;
    public String taskName;
    public int color;
    public Integer parent;
    public long beginDate;
    public long endDate;

    public int repeatInDays;

    public Task(int ID,
                String name,
                String color,
                Integer parent,
                String sDate,
                String eDate,
                int repeat) {
        this.ID = ID;
        this.taskName = name;
        this.color = Color.parseColor(color);
        this.parent = parent;
        this.beginDate = parseDate(sDate);
        this.endDate = parseDate(eDate);
        this.repeatInDays = repeat;
    }

    private long parseDate(String eDate) {
        return 1;
    }

}
