import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
public class QuizCardPlayer implements Serializable
{
	JTextArea  question;
	JFrame frame;
	int count=0,m=0,n=0;
	boolean flag =true;
	ArrayList<String> arr = new ArrayList<>();
	String[] str = new String[2];
	public static void main(String[] args) {
		QuizCardPlayer ss = new QuizCardPlayer();
		ss.go();
	}
	class Load implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			m=0;
			n++;
			try
			{
				File file = new File("hellooo.txt");
				FileReader read = new FileReader(file);
				BufferedReader bfread = new BufferedReader(read);
				String line;
				while((line=bfread.readLine())!=null)
				{
				  if(m<n)
				  {
				    str = line.split("/");
                    m++;
				  }
				  if(flag)
				  {         
				  	count++;
				  }         
				}           
				flag=false; 
				if(n==count)
					n=0;    
				System.out.println(n+"===="+count);
				question.setText(str[0]);
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
		}
	}
	class Show implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			question.setText(str[1]);
		}
	}
	public void go()
	{
       JLabel qLabel = new JLabel();
	   question = new JTextArea(6,40);
	   question.setLineWrap(true);

	   frame = new JFrame("QUIZCARDPLAYER");
	   JScrollPane scroll = new JScrollPane(question);
	   	scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    	scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    	JButton btn = new JButton("Show Answer");
    	JPanel panel = new JPanel();
    	panel.add(qLabel);
    	panel.add(scroll);
    	panel.add(btn);
    	JMenuBar menu = new JMenuBar();
    	JMenu filename = new JMenu("file");
    	JMenuItem save = new JMenuItem("save");
    	JMenuItem load = new JMenuItem("load");
    	load.addActionListener(new Load());
    	btn.addActionListener(new Show());
    	filename.add(save);
    	filename.add(load);
    	menu.add(filename);
    	frame.setJMenuBar(menu);
    	frame.getContentPane().add(BorderLayout.CENTER,panel);
    	frame.setSize(600,500);
    	frame.setVisible(true);
	}

}
