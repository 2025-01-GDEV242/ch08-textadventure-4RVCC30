/**
 * Representations for all the valid command words for the game
 * along with a string in a particular language.
 * 
 * @author  Emery Vallejo
 * @version 2025.03.29
 */
public enum CommandWord
{
    // A value for each command word along with its
    // corresponding user interface string.
    GO("go"),DROP("drop") ,QUIT("quit"), HELP("help"),LOOK("look"),BACK("back"),GRAB("grab"),INV("inv"),UNKNOWN("?");
    
    // The command string.
    private String commandString;
    
    /**
     * Initialise with the corresponding command string.
     * @param commandString The command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }
}
