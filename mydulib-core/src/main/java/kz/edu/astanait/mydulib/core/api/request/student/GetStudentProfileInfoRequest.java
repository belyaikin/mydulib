package kz.edu.astanait.mydulib.core.api.request.student;

import kz.edu.astanait.mydulib.core.api.MyDuRequest;
import kz.edu.astanait.mydulib.core.api.response.student.GetStudentProfileInfoResponse;

public record GetStudentProfileInfoRequest(long userId) implements MyDuRequest<GetStudentProfileInfoResponse> {
    @Override
    public String getPath() {
        return "https://my-du.astanait.edu.kz/api/user/students/profileInfo/" + userId;
    }

    @Override
    public Class<GetStudentProfileInfoResponse> getResponseType() {
        return GetStudentProfileInfoResponse.class;
    }
}