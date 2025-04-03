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
 * @author  Emery Vallejo
 * @version 2025.03.29
 */

public class Game 
{
    private Parser parser;
    private Player player;
    private ArrayList<Room> roomHistory = new ArrayList<Room>();
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = new Player();
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        /*                          
         *                  __________________
         *                 |???               |
         *                 |Items: Silouette  |
         *                  ------------------
         *                          /\
         *                         /  \
         * ______________   ------------------  __________________
         * |House       | / |Mall closing    |\ |School Hall     |
         * |Items: Game,|/  |Items: Pamphlet | \|Items: Text Book|
         * |Sillouette, |\  |Toy Train       | /|Temporary ID    |
         * |Baby Carrot | \ |Sillouette      |/ |Sillouette      |
         * --------------   ------------------  ------------------
         *                         \  /                \  /
         *                          \/                  \/
         *                   ------------------  ------------------
         *                   |Main Street     |  |PoolSideDriveway|
         *                   |Items:Sillouette|  |Items:Sillouette|
         *                   |You,            |  |Phone           |
         *                   |Stick           |  |Pool            |
         *                   ------------------  ------------------
         *                          \  /               \  /
         *                           \/                 \/
         * --------------    ------------------  ------------------
         * |Love        | / |Bedroom          |\ |Inside          |
         * |Items:Fear  |/  |Items: Sharp     | \|Items: Who      |
         * |Pain        |\  |Sillouette       | /|What            |
         * |Passion     | \ |Computer Video   |/ |Why             |
         * --------------   -------------------  ------------------
         * 
         */
        Room nowhere,house,mallClosing,schoolHall,mainStreet,poolSideDriveway,love,bedroom,inside;
        Item[] nowhereItems = {
                new Item("Sillouette", "It waves slowly still in the space, you're not sure if it's aware of itself.", false)},
                
               houseItems = {
                new Item("Game", "An old games console, it lacks the sleek design of a Playstaion or Xbox. Instead it's made up of rounded shapes with a slot for a cartridge and a smiling cartoon character.",true),
                new Item("Sillouette", "It hangs at the center of the room, small and fragile. Whenever the room shifts it seems to draw it's attention towards the \"most interesting\" thing in the room.",false),
                new Item("BabyCarrots","A bag of baby carrots, the sillouette seems to be staring intently at it.",true)}, 
                
                mallItems = {
                new Item("Pamphlet", "You can't seem to make out the symbols on the page, you've definetly seen them but haven't learned them. The pictures are pretty though.",true),
                new Item("ToyTrain", "You see one toy train, then another, and another. So many trains! Something inside you warms.",true),
                new Item("Sillouette", "It holds you hand gently and tightly, as if protecting you. It doesn't have a face, but you feel it's smile",false)
                },
                schoolItems = {
                new Item("Text Book", "Boring.",true),
                new Item("Temporary-ID", "Never could remeber to bring your student ID with you. Plus, the lanyard is itchy.",true),
                new Item("Sillouette", "Doesn't matter. You'll never seem them again.",false)
                },
                mainStreetItems = {
                new Item("You","Why do you feel so sad, nervous, and alone? You'll move on.",false),
                new Item("Sillouette","It walks next to you, but not with you",false),
                new Item("Stick", "The sillouette holds it in it's hand. Passive protection",false)
                },
                poolItems = {
                new Item("Sillouette","It tosses itself back and forth in the pool like it's never been happier to do anything ever.",false),
                new Item("Phone", "You knew this was a bad idea. Yet you let it happen.",true),
                new Item("Pool", "You wade in the pool a little bit. You feel less nervous than before, relief wafts over you. You did it.",true)
                },
                loveItems = {
                new Item("Fear","Hesitation protects you?",false),
                new Item("Pain","Shows you really care?",false),
                new Item("Passion", "Comes and goes.",false)
                },
                bedroomItems = {
                new Item("Yeah","Ow... Huh.",false),
                new Item("Sillouette","Yeah, was gonna happen eventually",false),
                new Item("ComputerVideo", "Mumbo Jumbo has always been good noise.",false),
            },
                insideItems = {
                new Item("Who", "You!",false),
                new Item("What", "You!",false),
                new Item("Why", "Not!",false),
                };
      
        // create the rooms
        nowhere = new Room("???", nowhereItems);
        house = new Room("A fairly large house, inside are several rooms,but they all exist in one space. You can't walk, but the room shifts around you instead",houseItems);
        mallClosing = new Room("A Mall closing down for the end of the day, the only lights that seem to be on are the ones above you. Someone has hold of your hand", mallItems);
        schoolHall = new Room("At the end of the hall you can see two double sided doors, each step you take towards one the room shifts so you can never see what's on the other side",schoolItems);
        mainStreet = new Room("Family run shops of all kinds surrond your left and right, next to you something follows your every step but when you reach out, it fades",mainStreetItems);
        poolSideDriveway = new Room("The sun shines through the umbrella as you stress in a chair, you feel as if you've made a very big decision",poolItems);
        love = new Room("It fully surrounds",loveItems);
        bedroom = new Room("a normal bedroom, a strange situation",bedroomItems);
        inside = new Room("You",insideItems);
        //initialise room exits
        nowhere.setExit("south", mallClosing);

        house.setExit("east", mallClosing);
        
        mallClosing.setExit("north", nowhere);
        mallClosing.setExit("east", schoolHall);
        mallClosing.setExit("south",mainStreet);
        mallClosing.setExit("west", house);
        
        schoolHall.setExit("south",poolSideDriveway);
        schoolHall.setExit("west",mallClosing);
        
        mainStreet.setExit("north",mallClosing);
        mainStreet.setExit("south",bedroom);
        
        poolSideDriveway.setExit("north",schoolHall);
        poolSideDriveway.setExit("south",inside);
        
        love.setExit("east",bedroom);
        
        bedroom.setExit("north",mainStreet);
        bedroom.setExit("east",inside);
        bedroom.setExit("west",love);
        
        inside.setExit("north",poolSideDriveway);
        inside.setExit("west", bedroom);


        player.goToRoom(nowhere); // start game outside
        roomHistory.add(player.getCurrentRoom());
        
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
        System.out.println(player.getCurrentRoom().getLongDescription());
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
                
            case BACK:
                back(command);
                break;
            
            case GRAB:
                grab(command);
                break;
            
            case INV:
                player.showInventory();
                break;
                
            case DROP:
                drop(command);
                break;
                
        }
        return wantToQuit;
    }
    private void drop(Command command)
    {
        ArrayList<Item> items = player.getInventory();
        for (int i = 0; i < items.size() ;i++)
        {
            String commandToLower = command.getSecondWord().toLowerCase();
            if(commandToLower.equals(items.get(i).getName().toLowerCase()))
            {
                player.dropItem(items.get(i));
            }
        }
    }
    private void grab(Command command)
    {
        ArrayList<Item> items = player.getCurrentRoom().getItemsInRoom();
        for (int i = 0; i < items.size() ;i++)
        {
            String commandToLower = command.getSecondWord().toLowerCase();
            if(commandToLower.equals(items.get(i).getName().toLowerCase()))
            {
                player.pickUpItem(items.get(i));
            }
        }
    }
    private void back(Command command)
    {
        if(roomHistory.size() <= 1)
        {
            System.out.println("You can't go any further back!");
            return;
        }
        
        if(!command.hasSecondWord()) {
            player.goToRoom(roomHistory.get(roomHistory.size() - 2));
            roomHistory.remove((roomHistory.size() - 1));
            roomHistory.remove((roomHistory.size() - 1));
            System.out.println(player.getCurrentRoom().getLongDescription());
            return;
        }

        int howFarBack = Integer.parseInt(command.getSecondWord());
        if (howFarBack < roomHistory.size())
        {
            Room nextRoom = roomHistory.get(roomHistory.size() - (1 + howFarBack));
            for(int i = 0; i < howFarBack; i++)
            {
                roomHistory.remove(roomHistory.size() - (1+i));
            }
            player.goToRoom(nextRoom);
            System.out.println(player.getCurrentRoom().getLongDescription());
            roomHistory.add(player.getCurrentRoom());
            
       
        }else
        {
            System.out.println("You can't go back that far.");
            System.out.println("You can go back " + (roomHistory.size() - 1) + " Steps.");
        }
    }
    private void look()
    {
        System.out.println(player.getCurrentRoom().getLongDescription());
        System.out.println("Items in this room: ");
        int i = 1;
        for(Item item : player.getCurrentRoom().getItemsInRoom())
        {
            System.out.println(i + ": " + item.getName() + " ");
            i++;
        }
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
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            player.goToRoom(nextRoom);
            System.out.println(player.getCurrentRoom().getLongDescription());
            roomHistory.add(player.getCurrentRoom());
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
