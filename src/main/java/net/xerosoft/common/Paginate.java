package net.xerosoft.common;

import lombok.Data;

import java.util.List;

@Data(staticConstructor = "of")
public class Paginate<T> {
    private final List<T> content;
    private final int offset;
    private final int limit;
    private final int count;
    private final int total;
}
