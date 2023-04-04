import demoConsoleApp.Utility.Action;
import demoConsoleApp.Utility.Action1;
import demoConsoleApp.Utility.Func;

public class Command {
    protected final String command;
    private final int index;
    private final Action handle;

    public int getIndex() {
        return index;
    }

    public Command(int index, String command, Action handle) {
        this.command = command;
        this.index = index;
        this.handle = handle;
    }

    @Override
    public String toString() {
        return index + ". " + command;
    }
    public void Handle(){
        handle.Invoke();
    }
}
