package org.edy.main;

import org.edy.input.Input;
import org.edy.output.Output;

public class Main
{
	public static String folder = "D:/var/log";

	public static void main(String[] args)
	{
		Input input = new Input();
		Output save = new Output();
		
		input.start(folder);
		save.saveFile(input.getAssembleList());
	}
}
