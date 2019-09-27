package ru.vtarasov.sb.student;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author vtarasov
 * @since 21.09.2019
 */
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Student {
    @Setter
    @Null
    private String id;

    @NotEmpty
    private final String name;

    @Min(16)
    private final int age;
}
