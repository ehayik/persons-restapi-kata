package org.github.ehayik.kata.persons.fixtures.verifies;

import static lombok.AccessLevel.PACKAGE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;

@RequiredArgsConstructor(access = PACKAGE)
public class ThrowingCallableVerify {

    private final ThrowingCallable throwingCallable;

    public void shouldThrowRuntimeException(String expectedMessage) {
        shouldThrowException(RuntimeException.class, expectedMessage);
    }

    private void shouldThrowException(Class<? extends Throwable> type, String expectedMessage) {
        assertThatThrownBy(throwingCallable).isInstanceOf(type).hasMessage(expectedMessage);
    }
}
