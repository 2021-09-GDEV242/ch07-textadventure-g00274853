/**
 * Representations for all the valid command words for the game
 * along with a string in a particular language.
 * 
 * @author  Yixuan Sun
 * @version 2021.10.25
 */
public enum CommandWord
{
    // A value for each command word along with its
    // corresponding user interface string.
    // add look 8.14
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"),LOOK("look"),EAT("eat"),
     USE("use"), HEALTH("health"),BACK("back");
    
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
