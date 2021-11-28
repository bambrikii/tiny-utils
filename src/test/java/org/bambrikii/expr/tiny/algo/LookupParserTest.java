package org.bambrikii.expr.tiny.algo;

import org.bambrikii.expr.tiny.parser.ExpressionParser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LookupParserTest {
    @Test
    public void shouldEval() {
        Object val = ExpressionParser.buildDefault()
                .consume("1 + $param1")
                .eval(new ExpressionAlgoContext(o -> {
                    if ("param1".equals(o)) {
                        return 2;
                    }
                    return null;
                }));

        assertThat(val).isEqualTo(3.0);
    }
}