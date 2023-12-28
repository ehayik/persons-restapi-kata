package org.github.ehayik.kata.persons.fixtures.verifies;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;

@NoArgsConstructor(access = PRIVATE)
public class Verifications {

    public static ThrowingCallableVerify verifyThat(ThrowingCallable throwingCallable) {
        return new ThrowingCallableVerify(throwingCallable);
    }
}
