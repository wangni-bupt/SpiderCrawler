package com.sipder.control;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.sipder.Action.EnterAction;



public class Controler extends JFrame{
	public static final int ROWS=4;
    public static final int COLS=20;//行列数
    private JLabel label;
    private JLabel labelFont;
    private JPanel comboPanel;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private static final int DEFAULT_SIZE=14;
    private static final int DEFAULT_SIZE2=10;
    private JComboBox<String> faceCombo;
    private JPanel panelfirst;
    private JPanel panelsecond;
    private JPanel panel;
    private JComboBox<String> text;

    public Controler()
    {
    	label=new JLabel("请输入需要爬取的网址：");
    	label.setFont(new Font("SanSerif",Font.BOLD,DEFAULT_SIZE));
    	panelfirst=new JPanel();
    	panelfirst.add(label,BorderLayout.NORTH);
    	textArea=new JTextArea(ROWS,COLS);//设置文本区
        scrollPane=new JScrollPane(textArea);//设置滚动窗格
        panelfirst.add(scrollPane,BorderLayout.EAST);
        add(panelfirst,BorderLayout.NORTH);
        
        labelFont=new JLabel("爬取的文件和相应格式（默认）：");
        labelFont.setFont(new Font("SanSerif",Font.BOLD,DEFAULT_SIZE2));
        text=new JComboBox<>();
        text.addItem("博文作者");
        text.addItem("作者博客地址");
        text.addItem("博文内容");
        text.addItem("推荐数");
        text.addItem("评论数");
        text.addItem("阅读数");
        text.addItem("发布时间");
        text.addItem("博文url");
        text.addItem("所有");
    	panelsecond=new JPanel();
    	panelsecond.add(labelFont,BorderLayout.WEST);
    	panelsecond.add(text,BorderLayout.CENTER);
    	faceCombo=new JComboBox<>();
    	faceCombo.addItem("txt");
    	comboPanel=new JPanel();
    	comboPanel.add(faceCombo);
    	panelsecond.add(comboPanel,BorderLayout.EAST);
    	add(panelsecond,BorderLayout.CENTER);
    	
        panel=new JPanel();
        JButton enterButton=new JButton ("确认");//设置确认键
        panel.add(enterButton);
        EnterAction enterAction=new EnterAction(textArea,text);
        enterButton.addActionListener(enterAction);//添加监听器
        add(panel,BorderLayout.SOUTH);
        pack();
    }

}
