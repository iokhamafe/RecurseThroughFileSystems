/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc385module3;

/**
 *
 * @author iiokh
 */
public class Driver
{
	
	public static void main(String[] args)
	{
		GUI gui = new GUI();
		DirectoryLister dl = new DirectoryLister(gui);
		gui.registerModel(dl);
	}
	
}