package kz.edu.astanait.mydulib.core.api.request;

import java.util.List;

public record FiltersBody(
        List<Filter> filters,
        int start,
        int size
) {
    public record Filter(
            String id,
            String value
    ) {}
}