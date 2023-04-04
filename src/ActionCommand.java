import demoConsoleApp.Utility.Action;
public class ActionCommand extends Command{
    public ActionCommand(int index, String command, Action handle) {
        super(index, command, handle);
    }

    @Override
    public String toString() {
        return command;
    }
}
