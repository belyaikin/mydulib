package kz.edu.astanait.mydulib.core.api.response.schedule;

import java.util.List;

public record GetScheduleResponse(
        int studyYear,
        int term,
        int weekNumber,
        List<DaySchedule> slots
) {
}
