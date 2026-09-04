package kz.edu.astanait.mydulib.core.api.response.student;

public record GetStudentProfileInfoResponse(
        long studentId,
        long userId,
        String foto, // really?
        String lastname,
        String firstname,
        String patronymic,
        IdAndName statusType,
        int barcode,
        String corporateEmail,
        String email,
        IdAndName gop,
        IdAndName eduProgram,
        IdAndName group,
        IdAndName degree,
        int course,
        long contractFileId,
        EducationalInstitution eduInstitution
) {
    private record IdAndName(long id, String name) {}
    private record EducationalInstitution(long id, String name, boolean graduatedFromEligibleInstitution) {}
}
