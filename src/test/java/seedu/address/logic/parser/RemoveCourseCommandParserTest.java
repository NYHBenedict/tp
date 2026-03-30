package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemoveCourseCommand;

public class RemoveCourseCommandParserTest {
    private RemoveCourseCommandParser parser = new RemoveCourseCommandParser();

    @Test
    public void parse_validCourseCode_success() {
        String input = "c/CS2103T";
        String validCourseCode = "CS2103T";
        RemoveCourseCommand expectedCommand = new RemoveCourseCommand(Arrays.asList(validCourseCode));

        assertParseSuccess(parser, input, expectedCommand);
    }

    @Test
    public void parse_courseCodeWithLeadingWhitespace_success() {
        String validCourseCode = "CS2101";
        RemoveCourseCommand expectedCommand = new RemoveCourseCommand(Arrays.asList(validCourseCode));

        // Leading whitespace should be trimmed
        assertParseSuccess(parser, "   c/" + validCourseCode, expectedCommand);
    }

    @Test
    public void parse_courseCodeWithTrailingWhitespace_success() {
        String validCourseCode = "CS2100";
        RemoveCourseCommand expectedCommand = new RemoveCourseCommand(Arrays.asList(validCourseCode));

        // Trailing whitespace should be trimmed
        assertParseSuccess(parser, "c/" + validCourseCode + "   ", expectedCommand);
    }

    @Test
    public void parse_multipleCourseCodes_success() {
        String input = "c/CS2103T,CS2101";
        RemoveCourseCommand expectedCommand = new RemoveCourseCommand(Arrays.asList("CS2103T", "CS2101"));

        assertParseSuccess(parser, input, expectedCommand);
    }

    @Test
    public void parse_missingPrefix_failure() {
        assertParseFailure(parser, "CS2103T",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCourseCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyCourseCode_failure() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCourseCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_whitespaceOnlyInput_failure() {
        assertParseFailure(parser, "   ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCourseCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_courseCodeWithSpaces_failure() {
        assertParseFailure(parser, " c/CS 2103T",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCourseCommand.MESSAGE_USAGE));
    }
}
