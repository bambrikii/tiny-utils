package org.bambrikii.expression.tiny.algo;

import org.assertj.core.util.Arrays;
import org.bambrikii.expression.tiny.parser.ExpressionParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ExpressionParserTest {
    public static List<Object> params() {
        return Arrays.asList(
                new Object[]{
                        new Object[]{"1+2", 3.0d},
                        new Object[]{"(1+2)", 3.0d},
                        new Object[]{"1+2*4", 9.0d},
                        new Object[]{"1+2*4-5", 4.0d},
                        new Object[]{"1 + 2 * ( 2 - 3) / 4", 0.5d},
                        new Object[]{"(1+2+3)*4", 24.0d},
                        new Object[]{"10/(1+2*3)*4", 5.714285714285714d},
                        new Object[]{"2021-11-28T14:50:45 + 1 day", LocalDateTime.of(2021, 11, 29, 14, 50, 45)}
                }
        );
    }

    @ParameterizedTest
    @MethodSource("params")
    public void shouldEval(String expression, Object expected) {
        Object val = ExpressionParser.buildDefault().consume(expression).eval(new ExpressionAlgoContext());
        assertThat(val).isEqualTo(expected);
    }
}
