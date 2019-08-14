package com.sipder.Action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sipder.webmagic.BlogPageProcessor;

import us.codecraft.webmagic.Spider;


public class EnterAction implements ActionListener//监听器
{
       private static int Max=5;//所需线程
       JTextArea jTextarea;
       JComboBox<String> text;
	   String s;//将文本区的内容导入s

       public EnterAction(JTextArea jTextarea,JComboBox<String> text) { 
    	   this.jTextarea=jTextarea; 
    	   this.text=text;
       }
       
       public void actionPerformed(ActionEvent event) {	
    	   long startTime, endTime;
    	   int number=0;
		   System.out.println("========爬虫启动=========");
		   startTime = new Date().getTime();
		   String LINE="";
		   s=jTextarea.getText();
		   BufferedReader br=new BufferedReader(new StringReader(s));
		   try{
			   LINE=br.readLine();
			   while(LINE!=null)
               {    
				   BlogPageProcessor blogprocess=new BlogPageProcessor();
				   blogprocess.setdata(text.getSelectedItem().toString());
				   Spider.create(blogprocess).addUrl(LINE).thread(5).run();
				   LINE=br.readLine();
				   number+=blogprocess.getnum();
			   }
            }catch (Exception e) {
            	e.printStackTrace();
            }  
			endTime = new Date().getTime();
			System.out.println("========爬虫结束=========");
			System.out.println("一共爬到" + number + "篇博客！用时为：" + (endTime - startTime) / 1000 + "s");
		}  //https://blog.hellobi.com/hot/weekly?page=1
 }