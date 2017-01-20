package pkk.interview.book;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderParserValidatorTest {
    private OrderParser.Validator validator;

    @Before
    public void setUp() throws Exception {
        validator = new OrderParser.Validator();
    }

    @Test
    public void validate() throws Exception {
        String line = "Q1/O/N/1.31/1000000";
        assertTrue(validator.validate(line));
    }

    @Test
    public void validate_illegalArgument_0() throws Exception {
        String[] lines = new String[]{
                "Q1/O/N/1.31",
                "Q1/O/N/1.31/1.1",
                "Q1/O/P/1.31/1000",
                "Q1/W/N/1.31/1000"
        };

        for (String line : lines) {
            assertFalse(validator.validate(line));
        }
    }
}