public class Command {
    private final String command;
    private final int index;
    private final Func handle;

    public int getIndex() {
        return index;
    }

    public Command(int index, String command, Func delagation) {
        this.command = command;
        this.index = index;
        this.handle = delagation;
    }

    @Override
    public String toString() {
        return index + ". " + command;
    }
    public void Handle(){
        handle.Invoke();
    }
}
