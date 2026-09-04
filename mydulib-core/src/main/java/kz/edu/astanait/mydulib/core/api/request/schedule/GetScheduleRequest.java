package kz.edu.astanait.mydulib.core.api.request.schedule;

import kz.edu.astanait.mydulib.core.api.HttpMethod;
import kz.edu.astanait.mydulib.core.api.MyDuRequest;
import kz.edu.astanait.mydulib.core.api.request.FiltersBody;
import kz.edu.astanait.mydulib.core.api.response.schedule.GetScheduleResponse;

import java.util.List;

public record GetScheduleRequest(
        int studyYear,
        int term,
        int weekNumber,
        int start,
        int size
) implements MyDuRequest<GetScheduleResponse> {
    @Override
    public HttpMethod getMethod() {
        return HttpMethod.POST;
    }

    @Override
    public String getPath() {
        return "/api/edu-process/classSchedule/student/me/search";
    }

    @Override
    public Object getBody() {
        return new FiltersBody(
                List.of(
                        new FiltersBody.Filter("studyYear", String.valueOf(studyYear)),
                        new FiltersBody.Filter("term", String.valueOf(term)),
                        new FiltersBody.Filter("weekNumber", String.valueOf(weekNumber))
                ),
                start,
                size
        );
    }

    @Override
    public Class<GetScheduleResponse> getResponseType() {
        return GetScheduleResponse.class;
    }
}
