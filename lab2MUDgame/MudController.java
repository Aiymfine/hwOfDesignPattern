import java.util.*;
public class MudController{
    private Player player;
    private boolean running=true;
    public MudController(Player player){
        this.player=player;
    }
    public void runGameLoop(){
        Scanner scan=new Scanner(System.in);
        while(running){
            System.out.print("=> ");
            String in=scan.nextLine();
            handleInput(in);
        }
    }
    private void handleInput(String in){
        String[] parts=in.trim().split(" ", 2);
        String command=parts[0].toLowerCase();
        String argument=parts.length>1? parts[1].toLowerCase() : "";

        switch(command){
            case "look":
            lookAround();
            break;

            case "move":
            move(argument);
            break;

            case "pick":
            if(argument.startsWith("up ")) pickUp(argument.substring(3));
            else System.out.println("not correct");
            break;

            case "inventory":
            checkInventory();
            break;

            case "help":
            showHelp();
            break;

            case "open":
            if(argument.equals("door")) openDoor();
            else System.out.println("its not the door");
            break;

            case "exit":
            running=false;
            break;
            default: System.out.println("choose other command");

        }
    }
    private void lookAround(){
        Room room=player.getCurrentRoom();
        System.out.println("Room is " + room.describe());
        System.out.println("Items : " + (room.getItems().isEmpty() ? "none" : room.getItems().stream().map(Item::getName).toList()));


    }
    private void move(String direction){
        Room nextRoom=player.getCurrentRoom().getConnection(direction);
        if(nextRoom==null){
            System.out.println("find another place");
        }
        else if(nextRoom.isDoorLocked()){
            System.out.println("door locked");
        }
        else {
            player.setCurrentRoom(nextRoom);
            System.out.println("You move " + direction);
            lookAround();

        }
    }
    private void pickUp(String itemName){
        Room room=player.getCurrentRoom();
        for(Item item:room.getItems()){
            if(item.getName().equalsIgnoreCase(itemName)){
                player.addItem(item);
                room.removeItem(item);
                System.out.println("You picked up the " + itemName);
                return;
            }
        }
        System.out.println("there is no item such: " + itemName);

    }
    private void checkInventory(){
        if(player.getInventory().isEmpty()){
            System.out.println("ur inventory is empty");
        } else{
            System.out.println("You have: " + player.getInventory().stream().map(Item::getName).toList());

        }
    }
    private void showHelp(){
        System.out.println("your commands:");
        System.out.println("look, move <direction>, pick up <item>, inventory, open door, help, exit");

    }
    private void openDoor(){
        Room current=player.getCurrentRoom();
        if(current.isDoorLocked()){
            current.unlockDoor();
            System.out.println("you unlocked the door");

        } else{
            System.out.println("there is no locked door");

        }
    }
    public static void main(String[] args){
        Room startRoom=new Room.Builder().description("starting room with a locked door")
        .addItem(new Item("key"))
        .doorLocked(true)
        .build();

        Player player=new Player(startRoom);
        MudController controller=new MudController(player);
        controller.runGameLoop();
    }
}