package kz.edu.astanait.mydulib.core.api.response.schedule;

import java.util.List;

public record DaySchedule(
        WeekDay weekDay,
        List<ClassItem> items
) {
}
