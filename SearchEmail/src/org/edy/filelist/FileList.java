package org.edy.filelist;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileList {

	List<File> file_list = new ArrayList<File>();
	List<File> directory_list = new ArrayList<File>();
	
	public List<File> getFile_list() {
		return file_list;
	}
	public void setFile_list(List<File> file_list) {
		this.file_list = file_list;
	}
	public List<File> getDirectory_list()
	{
		return directory_list;
	}
	public void setDirectory_list(List<File> directory_list)
	{
		this.directory_list = directory_list;
	}
	///////////////////////////////////////////////////////////
	public void addFile(File file){
		file_list.add(file);
	}
	
	public void addDirectory(File file){
		directory_list.add(file);
	}
	//////////////////////////////////////////////////////////
	@Override
	public String toString() {
		return "FileList [file_list=" + file_list + ", folder_list="
				+ directory_list + "]";
	}
}
