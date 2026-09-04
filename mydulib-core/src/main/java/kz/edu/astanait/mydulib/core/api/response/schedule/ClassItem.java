package kz.edu.astanait.mydulib.core.api.response.schedule;

public record ClassItem(
        long uid,
        ClassTime classTime,
        long academicGroupId,
        String academicGroupName,
        String lessonType,
        String subjectName,
        String langLevel,
        String sportSection,
        boolean online,
        String platform,
        String link,
        String building,
        String classroom,
        String teacherName,
        String replacementTeacherName
) {
}
