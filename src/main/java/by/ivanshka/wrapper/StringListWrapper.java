package by.ivanshka.wrapper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class StringListWrapper {
    private final List<String> strings;
}
