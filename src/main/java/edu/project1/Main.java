package edu.project1;

import edu.project1.commands.CommandExecutor;
import edu.project1.commands.CommandManager;
import edu.project1.io.Input;
import edu.project1.io.MyInput;
import edu.project1.io.MyOutput;
import edu.project1.io.Output;
import java.util.ArrayList;

public class Main {

    private Main() {
    }

    private static ArrayList<String> addWordsToDictionary() {
        ArrayList<String> dictionary = new ArrayList<>();
        dictionary.add("world");
        dictionary.add("person");
        dictionary.add("freedom");
        dictionary.add("java");
        return dictionary;
    }

    public static void main(String[] args) {
        ArrayList<String> dictionaryArray = addWordsToDictionary();
        Input input = new MyInput();
        Output output = new MyOutput();
        Dictionary dictionary = new Dictionary(dictionaryArray);
        CommandExecutor commandExecutor = new CommandExecutor();
        CommandManager commandManager = new CommandManager();

        HangMan hangMan = new HangMan(dictionary, input, output, commandExecutor, commandManager);

        hangMan.play();
    }
}
