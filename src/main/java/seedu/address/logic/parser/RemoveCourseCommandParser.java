package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE_CODE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.RemoveCourseCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RemoveCourseCommand object.
 */
public class RemoveCourseCommandParser implements Parser<RemoveCourseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * RemoveCourseCommand
     * and returns a RemoveCourseCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveCourseCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(" " + args.trim(), PREFIX_COURSE_CODE);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_COURSE_CODE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RemoveCourseCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_COURSE_CODE);

        try {
            String rawCourseCodes = argMultimap.getValue(PREFIX_COURSE_CODE).orElse("").trim();
            List<String> rawCodes = Arrays.stream(rawCourseCodes.split(","))
                    .map(String::trim)
                    .filter(code -> !code.isEmpty())
                    .collect(Collectors.toList());

            if (rawCodes.isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        RemoveCourseCommand.MESSAGE_USAGE));
            }

            List<String> courseCodes = new ArrayList<>();
            for (String rawCode : rawCodes) {
                courseCodes.add(ParserUtil.parseCourseCode(rawCode));
            }

            return new RemoveCourseCommand(courseCodes);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCourseCommand.MESSAGE_USAGE),
                    pe);
        }
    }
}
