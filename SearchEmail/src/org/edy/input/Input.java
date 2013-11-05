package org.edy.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.edy.filelist.FileList;

public class Input
{
	private List<String> assembleList = new ArrayList<String>();

	// 폴더 내에 있는 파일 및 하위폴더 목록
	private FileList searchFiles(File path)
	{
		FileList list = new FileList();

		File files[] = path.listFiles();
		for (File i : files)
		{

			if (i.isDirectory())
			{
				list.addDirectory(i);
				// System.out.println("folder : " + i.getName());
				continue;
			}
			// txt 파일만 목록에 집어 넣자..
			if (i.isFile() && i.getName().endsWith(".txt"))
			{
				list.addFile(i);
				// System.out.println("txt file : " + i.getName());
				continue;
			}

			// System.out.println("etc file : " + i.getName());
		}

		return list;
	}

	// 파일에서 이메일 검색하기
	private void searchEMail(List<File> list)
	{
		Iterator<File> it = list.iterator();
		// List<String> assembleList = new ArrayList<String>();
		// 폴더 파일명 건수 이메일 로 조립..
		// [파일 위치] [파일명] [건수] [이메일]
		// D:\var\log\test1 test1.txt 2 test1@google.com ; test12@yahoo.com
		// D:\var\log\test1\temp test2.txt 1 test123@google.com

		while (it.hasNext())
		{
			StringBuffer bfStr = new StringBuffer();
			File tmp = it.next();
			List<String> emails = readEMail(tmp);
			int size = emails.size();

			if (size > 0)
			{
				bfStr.append(tmp.getParent()).append("\t");
				bfStr.append(tmp.getName()).append("\t");

				bfStr.append(size).append("\t");

				for (int i = 0; i < size; i++)
				{
					bfStr.append(emails.get(i));
					if (i < size - 1)
					{
						bfStr.append(" ; ");
					}
				}

				// 조립한 내용을 리스트에 저장 - 결과저장..
				assembleList.add(bfStr.toString());
			}
		}
		// return assembleList;
	}

	// 한개의 파일에서 이메일 읽는 함수 - email만 리턴
	private List<String> readEMail(File file)
	{
		BufferedReader br = null;
		List<String> list = new ArrayList<String>();
		try
		{
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					file)));

			String str = null;
			while ((str = br.readLine()) != null)
			{
				if (str.matches("(^[0-9a-zA-Z_-]+@[0-9a-zA-Z]+[.][a-zA-Z]{2,4}$)"))
				{
					// System.out.println("E-Mail?? : " + str);
					// 이메일만 저장
					list.add(str);
				}
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (br != null)
			{
				try
				{
					br.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	// 파일로 부를 경우...
	private List<String> start(File file)
	{
		// txt파일 및 폴더 객체를 찾아내는 작업
		FileList list = searchFiles(file);

		// 이메일 스캔 후 결과로 만드는 작업
		// assembleList.addAll(searchEMail(list.getFile_list()));
		searchEMail(list.getFile_list());

		// 디렉토리 객체가 있을 경우 재귀호출
		List<File> directoryList = list.getDirectory_list();
		if (!(directoryList.isEmpty()))
		{
			for (File i : directoryList)
			{
				// assembleList.addAll(start(i));
				start(i);
			}
		}
		return assembleList;
	}

	// 문자열로 부를 경우..
	public void start(String path)
	{
		File file = new File(path);
		start(file);
	}

	// ////////////////////////////////////
	public List<String> getAssembleList()
	{
		return assembleList;
	}

	public void setAssembleList(List<String> assembleList)
	{
		this.assembleList = assembleList;
	}

}
