package com.sipder.control;

import java.awt.EventQueue;

import javax.swing.JFrame;


public class Control {
		 public static void main(String[] args)
	     {
	            EventQueue.invokeLater(()->
	                {
	                      JFrame frame=new Controler();
	                      frame.setTitle("WebCrawler");
	                      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                      frame.setVisible(true);
	                });
	     }

}
