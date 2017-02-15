/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module3;

/**
 *
 * @author iiokh
 */
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.awt.*;
import javax.swing.*;

/**
 * DirectoryLister class.
 * This class allows the user to recursively display the contents of a
 * selected directory in the file system.
 */
public class DirectoryLister
{
	
	// -----------------------------------------------------------------------
	// Attributes
	// -----------------------------------------------------------------------
	
	/** GUI used to display results */
	private GUI gui; 
	
	/** base path of directory to be traversed */
	private String basePath;

	
	// -----------------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------------
	
	/**
	 *	Create a new DirectoryLister that uses the specified GUI.
	 */
	public DirectoryLister(GUI gui)
	{
		this.gui = gui;
	}
	
	
	// -----------------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------------
	
	/**
	 *	Allow user to select a directory for traversal.
	 */
	public void selectDirectory()
	{
		// clear results of any previous traversal
		gui.resetGUI();
		
		// allow user to select a directory from the file system
		basePath = gui.getAbsoluteDirectoryPath();
		
		// update the address label on the GUI
		gui.setAddressLabelText(basePath);
		
		// traverse the selected directory, and display the contents
		showDirectoryContents(basePath);
	}
	
	
	/* the directory listing.
	  *	An error message is displayed if basePath does not represent a valid directory.
	  *	@param	basePath	the absolute path of a directory in the file system.
	  */
	public void showDirectoryContents(String basePath)
	{
	    //To do
            try {
                if (basePath == null || basePath.equals("")) {
                JOptionPane.showMessageDialog(null, "The specified filename is invalid! ", "Error", 
                                JOptionPane.ERROR_MESSAGE);
                }
                File someFileOrDirectory = new File(basePath); //Create File Object
                if (someFileOrDirectory.exists() == false) {
                    JOptionPane.showMessageDialog(null, "The specified filename is invalid! ", "Error", 
                                JOptionPane.ERROR_MESSAGE);
                } else {
                    enumerateDirectory(someFileOrDirectory); //Call enumerateDirectory Method
                }
            } catch (NullPointerException e) {
                
            }
            
            
	}
	
	
	/**
	 * Recursive method to enumerate the contents of a directory.
	 * @param f: Directory to enumerate
	 * @return : the size of the directory to enumerate. The size of a directory is the summation of the sizes of all files and sub-directories within that directory.
	 */
	private long enumerateDirectory(File f)
	{
	  
		//To do
            String isDirectory = "Directory";
            String isFile = "File";
            
            long fileSize = 0L;
            
            
            File[] theFiles = f.listFiles();
            
                if (f.isDirectory()) { //check if passed reference is a directory with no subdirectories (base Case)
                   for (File file : theFiles) {
                    fileSize += enumerateDirectory(file); //Calculate size recursively of directory by calling enumerateDirectory              
                       
                   }
                    gui.updateListing(f.getAbsolutePath(), getSizeString(fileSize), isDirectory, 
                                formattedDateString(f.lastModified()));
                } else  {

                    fileSize = f.length(); //Set fileSize equal to the specified files size
                    gui.updateListing(f.getAbsolutePath(), getSizeString(fileSize), isFile, 
                            formattedDateString(f.lastModified())); //Print File to Program 
                }         
   
            return fileSize;
	}
	/**
	 *	Convert a size from bytes to kilobytes and mega bites and return an appropriate descriptive string.
	 *	Example: 123456 bytes returns 120.56 KB
	 *  Example: 123456798 bytes returns 117.73
	 *	@param size		size, in bytes
	 *	@return			size, in kilobytes  + "KB" or size in megabytes + "MB"
	 */
	private String getSizeString(long size)
	{
		if (size <=1024*1024)
		{
			double kbSize = size/1024.0;
		
			return "" + String.format("%.2f", kbSize) + " KB";
		}
		else
		{
			double mbSize= size/(1024.0*1024.0);
			return "" +String.format("%.2f", mbSize)+" MB";
		}
	}
	
	
	/**
	 *	Return a numeric time value as a formatted date string.
	 *
	 *	@param		time	numeric time value in milliseconds
	 *	@return		formatted string using the format "MM/dd/yyyy hh:mm:ss aaa"
	 *				Example: 01/15/2010 12:37:52 PM
	 */
	private String formattedDateString(long time)
	{
		// create Date object from numeric time
		Date d = new Date(time);
		
		// create formatter
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aaa");

		// return formatted date string
		return sdf.format(d);
	}
}
