package org.edy.output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class Output
{
	public void saveFile(List<String> output)
	{
		//디렉토리 생성
		File directory = new File("d:/var/log/output");
		directory.mkdirs();
		
		//파일 저장
		File save = new File("d:/var/log/output/output.txt");
		PrintWriter pw = null;
		try
		{
			pw = new PrintWriter(save);
			for (int i = 0; i < output.size(); i++)
			{
				System.out.println("output : " + output.get(i));
				pw.println(output.get(i));
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (pw != null)
			{
				pw.close();
			}
		}
	}
}
