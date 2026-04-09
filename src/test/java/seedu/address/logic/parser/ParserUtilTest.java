package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.course.Course;

public class ParserUtilTest {
    private static final String INVALID_EMAIL = "example.com";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("1"));
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        assertEquals(VALID_EMAIL, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        assertEquals(VALID_EMAIL, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseCourseCode_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCourseCode(null));
    }

    @Test
    public void parseCourseCode_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, Course.MESSAGE_CONSTRAINTS, () -> ParserUtil.parseCourseCode("CS 2103T"));
    }

    @Test
    public void parseCourseCode_validValueWithWhitespace_returnsUppercaseTrimmedCode() throws Exception {
        assertEquals("CS2103T", ParserUtil.parseCourseCode(WHITESPACE + "cs2103t" + WHITESPACE));
    }
}
