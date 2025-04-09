package task05;

import task02.View;
import task02.ViewableResult;
import task04.ChangeConsoleCommand;
import task04.GenerateConsoleCommand;
import task04.Menu;
import task04.ViewConsoleCommand;

/**
 * The {@code Main} class represents the entry point of the application, where the program's menu is initialized
 * and commands are registered to be executed by the user. It creates and manages the menu, adds various console
 * commands, and starts the execution of the menu.
 */
public class Main {

    /** The view associated with the application, providing the viewable result. */
    private final View view = new ViewableResult().getView();

    /** The menu that holds and manages the console commands. */
    private final Menu menu = new Menu();

    /**
     * Initializes the menu with various console commands and executes the menu.
     * The commands include viewing, generating, changing, and executing tasks.
     */
    public void run() {
        // Add console commands to the menu
        menu.add(new ViewConsoleCommand(view));
        menu.add(new GenerateConsoleCommand(view));
        menu.add(new ChangeConsoleCommand(view));
        menu.add(new ExecuteConsoleCommand(view));

        // Execute the menu, allowing user interaction with commands
        menu.execute();
    }

    /**
     * The main method which serves as the entry point to the program.
     * It creates an instance of {@code Main} and runs the application.
     *
     * @param args command-line arguments passed to the application (not used in this case)
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}
