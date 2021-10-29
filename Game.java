import java.util.ArrayList;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Yixuan Sun
 * @version 2021.10.25
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Player player;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = new Player(1);
        createRooms();
        parser = new Parser();
    }

    public static void main(String[] args)
    {
        Game newGame = new Game();
        newGame.play();
    }
    
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room exit, cloakRoom, sittingRoomFirstFloor, restaurant, kitchen, 
        bathRoomFirstFloor, stairsBottom, stairsTop, basement, 
        sittingRoomSecondFloor, masterBedRoom, SecondBedRoom, SecondBedRoomBathRoom
        , bathRoomSecondFloor, bathRoom;
        
        Item key, chocolate, apple;
      
        //to eat
        chocolate = new Item("chocolate","chocolate bar", 2, true);
        apple = new Item("apple","apple juice", 1, true);

        // create the rooms
        exit = new Room("", true);
        cloakRoom = new Room("", true);
        sittingRoomFirstFloor = new Room("", true);
        restaurant = new Room("", true);
        kitchen = new Room("", true);
        bathRoomFirstFloor = new Room("", true);
        stairsBottom = new Room("", true);
        stairsTop = new Room("", true);
        basement = new Room("", true);
        sittingRoomSecondFloor = new Room("", true);
        masterBedRoom = new Room("", true);
        SecondBedRoom = new Room("", true);
        SecondBedRoomBathRoom = new Room("", true);
        bathRoomSecondFloor = new Room("", true);
        bathRoom = new Room("", true);
        
        // initialise room exits
        exit.setExit("west", cloakRoom);

        cloakRoom.setExit("west", sittingRoomFirstFloor);
        cloakRoom.setExit("east", exit);

        sittingRoomFirstFloor.setExit("west", bathRoomFirstFloor);
        sittingRoomFirstFloor.setExit("east", cloakRoom);
        sittingRoomFirstFloor.setExit("north", restaurant);
        sittingRoomFirstFloor.setExit("south", stairsBottom);
        
        bathRoomFirstFloor.setExit("north", kitchen);
        bathRoomFirstFloor.setExit("east", sittingRoomFirstFloor);
        
        restaurant.setExit("south", sittingRoomFirstFloor);
        restaurant.setExit("west", kitchen);
        
        kitchen.setExit("south", bathRoomFirstFloor);
        kitchen.setExit("east", restaurant);

        stairsBottom.setExit("up", stairsTop);
        stairsBottom.setExit("down", basement);
        
        basement.setExit("up", stairsBottom);

        stairsTop.setExit("west", sittingRoomSecondFloor);
        stairsTop.setExit("down", stairsBottom);
        
        sittingRoomSecondFloor.setExit("south", stairsTop);
        sittingRoomSecondFloor.setExit("west", bathRoom);
        sittingRoomSecondFloor.setExit("east", SecondBedRoom);
        sittingRoomSecondFloor.setExit("north", masterBedRoom);
        
        masterBedRoom.setExit("south", sittingRoomSecondFloor);
        masterBedRoom.setExit("west", bathRoomSecondFloor);
         
        bathRoomSecondFloor.setExit("east", masterBedRoom);
        bathRoomSecondFloor.setExit("south", bathRoom);
        
        bathRoom.setExit("north", bathRoomSecondFloor);
        bathRoom.setExit("west", sittingRoomSecondFloor);
        
        SecondBedRoom.setExit("west", sittingRoomSecondFloor);
        SecondBedRoom.setExit("east", SecondBedRoomBathRoom);
        
        SecondBedRoomBathRoom.setExit("west", SecondBedRoom);

        player.setRoom(SecondBedRoom);  
    }

     /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(player.getRoom().getLongDescription());
    }

     /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case LOOK:
                look();
                break;
                
            case EAT:
                eat(command);
                break;
            case USE:
                use(command);
                break;
            
            case HEALTH:
                printHealth();
                break;
                          
            
        }
        return wantToQuit;
    }
    //8.14 add look
    private void look()
    {
          System.out.println(player.getRoom().getLongDescription());
    }
    // 8.15 add eat
    private void eat(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("\nWhat do you want to eat?");
            return;
        }
        String foodItem = command.getSecondWord();
        if (!foodItem.equals("chocolate") && !foodItem.equals("candy")) {
            System.out.println("...this isn't edible.");
            return;
        } else if (foodItem.equals("chocolate")) {
            System.out.println("\nYou eat a chocolate bar. Your health increases by 2 spoops.");
            player.addHealth(2);
        } else if (foodItem.equals("apple")) {
            System.out.println("You eat apple. Your health increases by 1 spoops.");
            player.addHealth(1);
        } 
        Item currentItem = player.getItemFromInventory(foodItem);
        player.removeItem(currentItem);
    }
    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) 
        {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) 
        {
            System.out.println("There is no door!");
        }
        
        if (!nextRoom.isOpen()) 
        {
            System.out.println("\nThis door is locked. Use a key to unlock it.");
        }
        else 
        {
            player.setRoom(nextRoom);
            System.out.println(player.getRoom().getLongDescription());
            
        }
    }

    /**
     * Print the player's health to the console.
     */
    private void printHealth()
    {
        System.out.println("Your current health (in spoops): " + player.getHealth());
    }

    private void use(Command command)
    {
        if(!command.hasSecondWord()) 
        {
            // if there is no second word, we don't know what to use
            System.out.println("\nWhat item do you want to use?");
            return;
        }
        String itemName = command.getSecondWord();
        Item currentItem = player.getItemFromInventory(itemName);
        if (currentItem == null) 
        {
            System.out.println("\nYou don't have that item.");
            return;
        }
        if (itemName.equals("key")) 
        {
            System.out.println("\nWhich door? (use go command)");
            Command secondCommand = parser.getCommand();
            String direction = secondCommand.getSecondWord();
            Room nextRoom = player.getRoom().getExit(direction);
            nextRoom.unlock();
            System.out.println("\nYou unlocked the door. You wonder what could be on the other side.");
            player.removeItem(currentItem);
            return;
        }
        System.out.println("\nYou can't use this item.");
    }
    
    private void take(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to pick up
            System.out.println("\nWhat item do you want to take?");
            return;
        }
        String itemName = command.getSecondWord();
        Item currentItem = player.getRoom().getItemFromRoom(itemName);
        if (currentItem == null) {
            System.out.println("\nYou can't take that...");
            return;
        }
        if (player.pickUpItem(currentItem)) {
            System.out.println("\nYou picked up an item.");
        }
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
