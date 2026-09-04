package kz.edu.astanait.mydulib.core.api.response.schedule;

public record ClassTime(
        long id,
        int shiftNumber,
        int orderNumber,
        String title
) {}