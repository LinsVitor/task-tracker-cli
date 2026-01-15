import com.tasktracker.cli.CommandParser;
import com.tasktracker.exception.ServiceException;

void main(String[] args) {
    try {
        if (args.length == 2) {
            CommandParser.command(args[0], args[1]);
        } else if (args.length == 4) {
            CommandParser.command(args[0], args[1], args[2], args[3]);
        } else {
            CommandParser.command(args[0], args[1], args[2]);
        }
    } catch (ServiceException | IllegalArgumentException e) {
        IO.println(e.getMessage());
    }
}
