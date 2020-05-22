import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
public class QuizCardBuilder
{
    private JTextArea question;
    private JTextArea answer;
    private ArrayList<QuizCard> cardList;
    private JFrame frame;
    public static void main(String[] args) {
    	QuizCardBuilder builder = new QuizCardBuilder();
    	builder.go();
    }
    public void go()
    {
    	frame = new JFrame("Quiz Card Builder");
    	JPanel mainPanel = new JPanel();
    	Font bigFont = new Font("sanserif",Font.BOLD,24);
    	question = new JTextArea(6,20);
    	question.setLineWrap(true);
    	question.setWrapStyleWord(true);
    	question.setFont(bigFont);
    	JScrollPane qScroller = new JScrollPane(question);
    	qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    	qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    	answer = new JTextArea(6,20);
    	answer.setLineWrap(true);
    	answer.setWrapStyleWord(true);
    	answer.setFont(bigFont);
    	JScrollPane aScroller = new JScrollPane(answer);
    	aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    	aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    	JButton nextButton = new JButton("Next CARD");
    	cardList = new ArrayList<>();
    	JLabel qLabel = new JLabel("Question");
    	JLabel aLabel = new JLabel("Answer:");
    	mainPanel.add(qLabel);
        mainPanel.add(qScroller);
        mainPanel.add(aLabel);
        mainPanel.add(aScroller);
        mainPanel.add(nextButton);
        nextButton.addActionListener(new NextCardListener());
        JMenuBar menuBar = new JMenuBar();
        JMenu filename = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        newMenuItem.addActionListener(new NewMenuListener());
        saveMenuItem.addActionListener(new SaveMenuListener());
        filename.add(newMenuItem);
        filename.add(saveMenuItem);
        menuBar.add(filename);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setSize(500,600);
        frame.setVisible(true);

    }
    class SaveMenuListener implements ActionListener
    {
      public void actionPerformed(ActionEvent e)
      {
        QuizCard card = new QuizCard(question.getText(),answer.getText());
        cardList.add(card);
        JFileChooser fileSave = new JFileChooser();
        fileSave.showSaveDialog(frame);
        saveFile(fileSave.getSelectedFile());
      }
    }
    class NextCardListener implements ActionListener
    {
      public void actionPerformed(ActionEvent e)
      {
      	QuizCard card = new QuizCard(question.getText(),answer.getText());
      	cardList.add(card);
      	cardClear();
      }
    }
    class NewMenuListener implements ActionListener
    {
      public void actionPerformed(ActionEvent e)
      {
      	cardList.clear();
      	cardClear();
      }
    }
    private void cardClear()
    {
    	question.setText("");
    	answer.setText("");
    	question.requestFocus();
    }
    private void saveFile(File f)
    {
    	try
    	{
    		BufferedWriter writer = new BufferedWriter(new FileWriter(f));
    		for(QuizCard card:cardList)
    		{
    			writer.write(card.getQuestion()+"/");
    			writer.write(card.getAnswer()+"\n");
    		}
    		writer.close();
    	}
    	catch(Exception ex)
    	{
    		System.out.println("Coundn't write the cardList out");
    		ex.printStackTrace();
    	}
    }
}
class QuizCard
{
  String question;
  String answer;
  QuizCard(String q,String a)
  {
    question=q;
    answer=a;
  }
  public String getQuestion()
  {
    return question;
  }
  public String getAnswer()
  {
  	return answer;
  }
}