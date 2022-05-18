import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class iFace {

    public static int readIntegerField(Scanner input, int min, int max, Boolean isMenu) {
        Boolean read = false;
        int field = -1;
        while (!read)
        {
            try {
                field = input.nextInt();
                if (field < min || field > max) {
                    if (!isMenu) throw new InputMismatchException("Este número não está em um intervalo adequado."); 
                    else if (field != 99)
                        throw new InputMismatchException("Opção inválida."); 
                }
                break;
            } catch (InputMismatchException e) {
                if (e.getMessage() != null)
                    Graphics.failure("Entrada inválida! " + e.getMessage() + " Tente novamente.");
                else
                    Graphics.failure("Entrada inválida! Você não digitou um número. Tente novamente.");
                input.next();
            }
        }
        return field;
    }

    public static String readStringField(Scanner input) {
        Boolean read = false;
        String field = "";
        while (!read)
        {
            try {
                field = input.next().trim();
                break;
            } catch (NoSuchElementException e) {
                if (e.getMessage() != null)
                    Graphics.failure("Você precisa digitar uma entrada, tente novamente.");
                input.next();
            }
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