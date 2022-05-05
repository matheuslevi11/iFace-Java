import java.util.ArrayList;

public class Graphics implements Constants {
    
    public static void start(String name, String description)
    {
        System.out.println(BLUE+"---------------------------------------------------------------------------\n");
        System.out.println("                         Bem vindo ao "+name+"                           \n");
        System.out.println("---------------------------------------------------------------------------\n"+RESET);
        System.out.println(PURPLE+description+"\n"+RESET);
    }
    
    public static void printOptions(ArrayList<?> options, String title)
    {
        System.out.println(BLUE+"---------------------------------------------------------------------------"+RESET);
        System.out.println(PURPLE+title+RESET);
        System.out.println(BLUE+"---------------------------------------------------------------------------"+RESET);
        for (int i = 0; i < options.size(); i++) {
            System.out.println("["+(i+1)+"] " + options.get(i));
        }
        System.out.println(BLUE+"---------------------------------------------------------------------------"+RESET);
    }

    public static void success(String message)
    {
        System.out.println(GREEN+message+RESET);
    }
    
    public static void failure(String message)
    {
        System.out.println(RED+message+RESET);
    }
    
    public static void printMessages(ArrayList<Message> msgs, String title)
    {
        System.out.println(BLUE+"---------------------------------------------------------------------------"+RESET);
        System.out.println(PURPLE+title+RESET);
        System.out.println(BLUE+"---------------------------------------------------------------------------"+RESET);
        for (Message m : msgs) {
            System.out.println(m.getDate() + " " + m.getSender() + ": " + m.getContent());
        }
    }
}
