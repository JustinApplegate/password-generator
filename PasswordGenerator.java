import java.util.*;
import java.io.*;
public class PasswordGenerator {
  
  public static int length = 14;
  public static String lengthDefault = "                              (default)";
  public static String charset = "\n   - lowercase (i.e. abcde)\n   - uppercase (i.e. ABCDE)\n   - numbers (i.e. 01234)"+
      "\n   - basic symbols (i.e. !@#$ )\n   - advanced symbols (i.e. ~<;]+)";;
  public static String charsetDefault = "                                  (default)";
  public static boolean lowercase = true;
  public static boolean uppercase = true;
  public static boolean numbers = true;
  public static boolean basicsymbols = true;
  public static boolean advancedsymbols = true;
  
  public static void main(String[] args) throws IOException, InterruptedException {
    boolean generate = false;
    while (!generate) {
      printBegin();
      generate = commandInterpreter();
    }
    for (int a = 0; a < 10; a++) {
      generate();
      System.out.println();
    }
  }
  
  public static void printBegin() throws IOException, InterruptedException {
    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    System.out.println("");
    System.out.println("  PASSWORD_LENGTH:  "+length+lengthDefault);
    System.out.println("");
    System.out.println("  CHARACTER_SET:  "+charsetDefault+charset);
    System.out.println("_____________________________________________________________");
    System.out.println("");
    System.out.println("LIST OF COMMANDS:");
    System.out.println(" * GENERATE");
    System.out.println(" * EDIT [fieldname]");
    System.out.println(" * EXIT");
  }
  
  public static boolean commandInterpreter() {
    Scanner input = new Scanner(System.in);
    String command = "";
    boolean generate = false;
    boolean error = true;
    
    while (error) {
      error = false;
      System.out.println();
      System.out.print("Command>");
      command = input.nextLine();
      System.out.println();
      if (command.equalsIgnoreCase("generate")) {
        generate = true;
      }
      else if (command.equalsIgnoreCase("exit")) {
        System.out.println("Program ended.");
        System.exit(0);
      }
      else if (command.length()>=4&&command.substring(0,4).equalsIgnoreCase("edit")) {
        String field = "";
        if (command.length()==4) {
          System.out.print("Field>");
          field = input.nextLine();
          System.out.println();
        }
        else {
          String space = command.substring(4,5);
          if (!space.equals(" ")) {
            System.out.println("Error: invalid command ('"+command+"').");
            error = true;
          }
          else if (command.length()==5) {
            System.out.print("Field>");
            field = input.nextLine();
            System.out.println();
          }
          else {
            field = command.substring(5, command.length());
          }
        }
        if (field.equalsIgnoreCase("password_length")) {
          error = passwordLength();
        }
        else if (field.equalsIgnoreCase("character_set")) {
          characterSet();
        }
        else {
          System.out.println("Error: invalid command ('"+command+"').");
          error = true;
        }
      }
      else {
        System.out.println("Error: invalid command ('"+command+"').");
        error = true;
      }
    }
    return generate;
  }
  
  public static boolean passwordLength() {
    Scanner input = new Scanner(System.in);
    boolean error = false;
    String password_length = "";
    int password_length_int = 0;
    
    System.out.print("Password_Length>");
    password_length = input.nextLine();
    System.out.println();
    try {
      password_length_int = Integer.parseInt(password_length);
      if (password_length_int>0) {
        length = password_length_int;
        lengthDefault = "";
      }
      else {
        System.out.println("Error: invalid password_length ('"+password_length+"').");
        error = true;
      }
    }
    catch (Exception e) {
      System.out.println("Error: invalid password_length ('"+password_length+"').");
      error = true;
    }
    return error;
  }
  
  public static void characterSet() {
    Scanner input = new Scanner(System.in);
    boolean lowercase_temp = lowercase;
    boolean uppercase_temp = uppercase;
    boolean numbers_temp = numbers;
    boolean basicsymbols_temp = basicsymbols;
    boolean advancedsymbols_temp = advancedsymbols;
    boolean go = false;
    System.out.println("LIST OF COMMANDS:");
    System.out.println(" * SAVE");
    System.out.println(" * CANCEL");
    System.out.println(" * [optionname]");
    System.out.println();
    while (!go) {
      System.out.println("Options:");
      System.out.print(" * lowercase");
      if (lowercase_temp) {
        System.out.print(" X");
      }
      System.out.println();
      System.out.print(" * uppercase");
      if (uppercase_temp) {
        System.out.print(" X");
      }
      System.out.println();
      System.out.print(" * numbers");
      if (numbers_temp) {
        System.out.print(" X");
      }
      System.out.println();
      System.out.print(" * basic symbols");
      if (basicsymbols_temp) {
        System.out.print(" X");
      }
      System.out.println();
      System.out.print(" * advanced symbols");
      if (advancedsymbols_temp) {
        System.out.print(" X");
      }
      System.out.println();
      System.out.println("");
      System.out.print("Character_Set>");
      String password_check = input.nextLine();
      System.out.println();
      if (password_check.equalsIgnoreCase("lowercase")) {
        if (lowercase_temp) {
          lowercase_temp = false;
        }
        else {
          lowercase_temp = true;
        }
      }
      else if (password_check.equalsIgnoreCase("uppercase")) {
        if (uppercase_temp) {
          uppercase_temp = false;
        }
        else {
          uppercase_temp = true;
        }
      }
      else if (password_check.equalsIgnoreCase("numbers")) {
        if (numbers_temp) {
          numbers_temp = false;
        }
        else {
          numbers_temp = true;
        }
      }
      else if (password_check.equalsIgnoreCase("basic symbols")) {
        if (basicsymbols_temp) {
          basicsymbols_temp = false;
        }
        else {
          basicsymbols_temp = true;
        }
      }
      else if (password_check.equalsIgnoreCase("advanced symbols")) {
        if (advancedsymbols_temp) {
          advancedsymbols_temp = false;
        }
        else {
          advancedsymbols_temp = true;
        }
      }
      else if (password_check.equalsIgnoreCase("save")) {
        if (!lowercase_temp&&!uppercase_temp&&!numbers_temp&&!basicsymbols_temp&&!advancedsymbols_temp) {
          System.out.println("Error: no options selected.");
          System.out.println();
        }
        else {
          go = true;
          lowercase = lowercase_temp;
          uppercase = uppercase_temp;
          numbers = numbers_temp;
          basicsymbols = basicsymbols_temp;
          advancedsymbols = advancedsymbols_temp;
          charset = "";
          charsetDefault = "";
          if (lowercase) {
            charset += "\n   - lowercase (i.e. abcde)";
          }
          if (uppercase) {
            charset += "\n   - uppercase (i.e. ABCDE)";
          }
          if (numbers) {
            charset += "\n   - numbers (i.e. 01234)";
          }
          if (basicsymbols) {
            charset += "\n   - basic symbols (i.e. !@#$ )";
          }
          if (advancedsymbols) {
            charset += "\n   - advanced symbols (i.e. ~<;]+)";
          }
        }
      }
      else if (password_check.equalsIgnoreCase("cancel")) {
        go = true;
      }
      else {
        System.out.println("Error: invalid option ('"+password_check+"').");
        System.out.println();
      }
    }
  }
  
  public static void generate() {
    Random rn = new Random();
    int distribute = 1;
    int lowercase_num = 0;
    int uppercase_num = 0;
    int numbers_num = 0;
    int basicsymbols_num = 0;
    int advancedsymbols_num = 0;
    if (lowercase) {lowercase_num = distribute;distribute++;}
    if (uppercase) {uppercase_num = distribute;distribute++;}
    if (numbers) {numbers_num = distribute;distribute++;}
    if (basicsymbols) {basicsymbols_num = distribute;distribute++;}
    if (advancedsymbols) {advancedsymbols_num = distribute;distribute++;}
    distribute--;
    
    int[] password_chars = new int[length];
    boolean all = false;
    boolean[] char_sets = new boolean[distribute];
    while (!all) {
      for (int d = 0; d < distribute; d++) {
        char_sets[d] = false;
      }
      all = true;
      for (int a = 0; a < length; a++) {
        password_chars[a] = rn.nextInt(distribute)+1;
        char_sets[password_chars[a]-1] = true;
      }
      for (int c = 0; c < distribute; c++) {
        if (!char_sets[c]) {
          all = false;
        }
      }
    }
    
    String alphabetLowercase = "abcdefghijklmnopqrstuvwxyz";
    String alphabetUppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String alphabetNumbers = "0123456789";
    String alphabetBasicSymbols = "!@#$%^&*() ";
    String alphabetAdvancedSymbols = "`~-_=+[{]}\\|;:'\",<.>/?";
    char[] password = new char[length];
    
    for (int b = 0; b < length; b++) {
      if (password_chars[b]==lowercase_num) {
        password[b] = alphabetLowercase.charAt(rn.nextInt(26));
      }
      else if (password_chars[b]==uppercase_num) {
        password[b] = alphabetUppercase.charAt(rn.nextInt(26));
      }
      else if (password_chars[b]==numbers_num) {
        password[b] = alphabetNumbers.charAt(rn.nextInt(10));
      }
      else if (password_chars[b]==basicsymbols_num) {
        password[b] = alphabetBasicSymbols.charAt(rn.nextInt(11));
      }
      else if (password_chars[b]==advancedsymbols_num) {
        password[b] = alphabetAdvancedSymbols.charAt(rn.nextInt(12));
      }
      System.out.print(password[b]);
    }
  }
  
}