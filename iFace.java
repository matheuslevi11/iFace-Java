import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class iFace {

    public static int readIntegerField(Scanner input, int min, int max, Boolean isMenu) {
        int field = -1;
        while (field == -1)
        {
            try {
                field = input.nextInt();
                if (field < min || field > max) {
                    if (!isMenu) {
                        field = -1;
                        throw new InputMismatchException("Valor inválido."); 
                    }
                    else if (field != 99) {
                        field = -1;
                        throw new InputMismatchException("Opção inválida."); 
                    }
                }
                return field;
            } catch (InputMismatchException e) {
                if (e.getMessage() != null)
                    Graphics.failure("Entrada inválida! " + e.getMessage() + " Tente novamente.");
                else
                    Graphics.failure("Entrada inválida! Você não digitou um número. Tente novamente.");
                }
            input.nextLine();
        }
        return field;
    }

    public static String readStringField(Scanner input) {
        String field = "ERRO";
        while (field.equals("ERRO"))
        {
            try {
                field = input.next().trim();
                if (field.isEmpty()) {
                    field = "ERRO";
                    throw new NoSuchElementException("Campo não pode estar vazio!");
                }
                break;
            } catch (NoSuchElementException e) {
                if (e.getMessage() != null)
                    Graphics.failure("Você precisa digitar uma entrada, tente novamente.");
                }
            input.nextLine();
        }
        return field;
    }
    public static void main(String[] args) {
        ArrayList<String> options = new ArrayList<String>();
        options.add("PESSOA");
        options.remove("TESTE");
        System.out.println("Amantes de Academia".equalsIgnoreCase("Amantes de Academia"));
    }
}