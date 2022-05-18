import java.util.Scanner;
import java.util.ArrayList;

public class Profile {
    public String hobbie;
    public int idade;
    public String local;

    public Profile(String hobbie, int idade, String local)
    {
        this.hobbie = hobbie;
        this.idade = idade;
        this.local = local;
    }

    public String[] getProfileInfo()
    {
        String[] atts = {this.hobbie, String.valueOf(this.idade), this.local};
        return atts;
    }

    public static Profile createProfile() {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o seu hobbie:");
        String hobbie = input.next().trim();
        System.out.println("Digite a sua idade:");
        int idade = iFace.readIntegerField(input, 0, 120, false);
        System.out.println("Digite o seu local:");
        String local = input.next().trim();
        
        Profile p = new Profile(hobbie, idade, local);
        return p;
    }

    public void editProfile() {
        int choice;
        Scanner input = new Scanner(System.in);
        while (true)
        {
            ArrayList<String> options = Database.editOptions("Profile");
            Graphics.printOptions(options, "Escolha o campo que deseja editar");
            System.out.println("[99] Sair da edição");
            
            choice = input.nextInt();

            if (choice == 1)
            {
                System.out.println("Digite o novo hobbie:");
                String hobbie = input.next().trim();
                this.hobbie = hobbie;
            }
            if (choice == 2)
            {
                System.out.println("Digite a nova idade:");
                int idade = input.nextInt();
                this.idade = idade;
            }
            else if (choice == 3)
            {
                System.out.println("Digite o novo local:");
                String local = input.next().trim();
                this.local = local;
            }
            else if (choice == 99)     
                break;
            
        }
    }
}