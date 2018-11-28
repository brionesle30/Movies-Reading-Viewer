/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.function.Predicate;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
/**
 *
 * @author 12051033
 */
public class MovieApplication extends JFrame implements ActionListener
{
    final private JFrame frame;
    final private JMenuBar menuBar;
    
    final private JTextArea ta;
    //main menus
    final private JMenu menu1;
    final private JMenu menu2;
    final private JMenu menu3;
    final private JMenu menu4;
    //menu1 submenu 
    final private JMenuItem menuItem;
    final private JMenuItem menuItem1;
    //menu2 submenu
    final private JMenuItem menu2Item;
    final private JMenuItem menu2Item1;
    final private JMenuItem menu2Item2;
    //menu3 submenu
    final private JMenuItem menu3Item;
    final private JMenuItem menu4Item;
    final private JScrollPane scroll;
    String searchList;
    String moiveCntLoc;
    String inputCompare;
    //assign object movie to arraylist
    private List<Movie> myList;
    //Contrcutor
public MovieApplication()
{
    //window title
    super("Welcome to Moive readings!");
    //first menu
    frame = new JFrame();
    //first menu
    menu1 = new JMenu("Movie Data");
        //sub menu items
        //firts option
        menuItem = new JMenuItem("Read Data");
        menuItem.addActionListener(this);// actionlistener for data loading
        //second option
        menuItem1 = new JMenuItem("Save Data");
        menuItem1.addActionListener(this); //actionlistener for saving file
    menu1.add(menuItem);
    menu1.add(menuItem1);
    //Second menu
    menu2 = new JMenu("Activities");
        //sub menu items
        //firts option
        menu2Item = new JMenuItem("List Movies");
        menu2Item.addActionListener(this);
        //second option
        menu2Item1 = new JMenuItem("Sort Movies by Name");
        menu2Item1.addActionListener(this);
        //third option
        menu2Item2 = new JMenuItem("Search Movies by Ticket Price");
        menu2Item2.addActionListener(this);
    menu2.add(menu2Item);
    menu2.add(menu2Item1);
    menu2.add(menu2Item2);
   //Third menu
    menu3 = new JMenu("Distributors");
        //sub menu items
        //firts option
        menu3Item = new JMenuItem("Details");
        menu3Item.addActionListener(this);
    menu3.add(menu3Item);
    
    //forth menu
   menu4 = new JMenu("Exit");
       menu4Item = new JMenuItem("Exit");
       menu4Item.addActionListener(this);
   menu4.add(menu4Item);
        //sub menu items
    //Set menubar 
    menuBar = new JMenuBar();
    setJMenuBar(menuBar);
    menuBar.add(menu1);
    menuBar.add(menu2);
    menuBar.add(menu3);
    menuBar.add(menu4);
    //textArea
    ta = new JTextArea(25, 90);
    ta.setLineWrap(true);
    ta.setWrapStyleWord(true);
    ta.setEditable ( false ); 
    ta.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
    scroll = new JScrollPane (ta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
}
@Override
//event handler
public void actionPerformed(ActionEvent m1)
{
    if(m1.getSource()==menuItem)
    {   
        ta.setText("");
        readDataFileAndDisplay();//load data from the file read
    }    
    if(m1.getSource()==menuItem1)
    {
        ta.setText("");  
        saveFile();//save new file
    }
    if(m1.getSource()==menu2Item)
    {
        ta.setText("");
        listMovie();// list array of movie
    }
    if(m1.getSource()==menu2Item1)
    {
        ta.setText("");
        sortDisMovie(); //sort array movie into alphabetical orlder
    }
    if(m1.getSource()==menu2Item2)
    {
        ta.setText("");
        searchMovieC();//search by price
    }
    if(m1.getSource()==menu3Item)
    {    
        ta.setText("");
        distributorStream();//print distributor details
    }
    if(m1.getSource()==menu4Item)
    {
        int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Warning message", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.NO_OPTION) //if no application will continue
        {
        }else if(confirm == JOptionPane.YES_OPTION) 
        {
            System.exit(0); // if yes exit the application
        }
    }    
}//end of actionPerformed method
//read data file method
private void readDataFileAndDisplay() 
{
    myList = new LinkedList<>();
    int count=0;
    String allMovie="";
    try
    {
        try (Scanner in = new Scanner(new FileReader
        ("MovieData.txt"))
        .useDelimiter("\t,\\s*")) //open file
        {
            //set variable for token
            String myEntry = "";
            String movieName = "";
            String leadActorName = "";
            String leadActressName = "";
            String theatreName = "";
            String ticketPrice = "";
            while (in.hasNextLine()) //loop the current lines
            {
                myEntry = in.nextLine(); // return the rest of the current lines
                StringTokenizer st = new StringTokenizer(myEntry,","); // assign object StringTokenizer
                while(st.hasMoreTokens()) //test if there more token and return it on loop
                {
                    //initialized varaible
                    movieName = st.nextToken();
                    leadActorName = st.nextToken();
                    leadActressName = st.nextToken();
                    theatreName = st.nextToken();
                    ticketPrice = st.nextToken();
                    double tp1 = Double.parseDouble(ticketPrice);
                    myList.add(new Movie(movieName,leadActorName, leadActressName, theatreName, tp1));
                }
            } // end of while loop
            in.close();//close file
        }
        JOptionPane.showMessageDialog(null,"Read Data File successfully!","",
        JOptionPane.INFORMATION_MESSAGE); // message if file is successfully read
    }catch(FileNotFoundException e) // if file not found message 
    {
        ta.setText(String.format("=============================================================================================================================================\n"
        +"You must generate a MovieData.txt file or create it to read file.\n"+
        "============================================================================================================================================="));  
        JOptionPane.showMessageDialog(null,"No file found!","",
        JOptionPane.INFORMATION_MESSAGE);  int confirm = JOptionPane.showConfirmDialog(frame, "Generate file?", "Warning message", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.NO_OPTION) // if user choose no. nothing will be done
        {
            JOptionPane.showMessageDialog(null,"No file has been created!","",
            JOptionPane.INFORMATION_MESSAGE); 
        }else if (confirm == JOptionPane.YES_OPTION)  // the user choose yes file will be generated
        {
        try
        {
            //write a text file with following data
            FileWriter writer = new FileWriter("MovieData.txt", true);
            writer.write("The Dark Tower, Matthew McConaughey, Idris Elba,Harbour Town Cinema, 18\r\n");
            writer.write("Terminator 2, Arnold Schwarzenegger, Linda Hamilton,City Myer Cinema, 14\r\n");
            writer.write("All Saints, John Corbett, Cara Buono, Sunny Bank Cinema, 12\r\n");
            writer.write("Forrest Gump, Tom Hanks, Robin Wright, Reading Entertainment,330\r\n");
            writer.write("Ghost, Patrick Swayze, Demi Moore, Elizabeth City Centre,218\r\n");
            writer.write("Gone with the Wind, Clark Gable, Vivien Leigh, Elizabeth City Centre,199\r\n");
            writer.write("Good Will Hunting, Matt Damon, Minnie Driver, Harbour Town Cinema,138\r\n");
            writer.write("Grease, John Travolta, Olivia Newton, City Myer Cinema,181\r\n");
            writer.write("Halloween, Donald Pleasence, Jamie Lee Curtis, Sunny Bank Cinema,47\r\n");
            writer.write("Hard Rain, Morgan Freeman, Minnie Driver, Reading Entertainment,20\r\n");
            writer.write("Independence Day, Will Smith, Vivica A. Fox, City Myer Cinema,306\r\n");
            writer.write("Indiana Jones, Harrison Ford, Alison Doody, Sunny Bank Cinema,197\r\n");
            writer.write("Jaws, Roy Scheider, Lorraine Gary, Harbour Town Cinema,260\r\n");
            writer.write("Men in Black, Tommy Lee Jones, Tony Shalhoub, Elizabeth City Centre,250\r\n");
            writer.write("Multiplicity, Michael Keaton, Andie MacDowell, Reading Entertainment,20 \r\n");
            writer.write("Pulp Fiction, Quentin Tarantino, Uma Thurman, Reading Entertainment,108\r\n");
            writer.write("Raiders of the Lost Ark, Frank Marshall, Karen Allen, Elizabeth City Centre,242\r\n");
            writer.write("Saving Private ryan, Tom Hanks, Vin Diesel, Elizabeth City Centre,178\r\n");
            writer.write("Schindler's List, Liam Neeson, Embeth Davidtz, Harbour Town Cinema,96\r\n");
            writer.write("Scream, David Arquette, Neve Campbell, City Myer Cinema,103\r\n");
            writer.write("Speed 2, Jason Patric, Sandra Bullock, Sunny Bank Cinema,48\r\n");
            writer.write("Terminator, Arnold Schwarzenegger, Linda Hamilton, Reading Entertainment,37\r\n");
            writer.write("The Fifth Element, Bruce Willis, Milla Jovovich, City Myer Cinema,64\r\n");
            writer.write("Ghost, Patrick Swayze, Demi Moore, Elizabeth City Centre,218\r\n");
            writer.write("Gone with the Wind, Clark Gable, Vivien Leigh, Elizabeth City Centre,199\r\n");
            writer.write("Good Will Hunting, Matt Damon, Minnie Driver, Harbour Town Cinema,138\r\n");
            writer.write("Grease, John Travolta, Olivia Newton, City Myer Cinema,181\r\n");
            writer.write("Hard Rain, Morgan Freeman, Minnie Driver, Reading Entertainment,20\r\n");
            writer.write("Independence Day, Will Smith, Vivica A. Fox, City Myer Cinema,306\r\n");
            writer.write("Indiana Jones, Harrison Ford, Alison Doody, Sunny Bank Cinema,197\r\n");
            writer.write("Jaws, Roy Scheider, Lorraine Gary, Harbour Town Cinema,260\r\n");
            writer.write("Men in Black, Tommy Lee Jones, Tony Shalhoub, Elizabeth City Centre,250\r\n");
            writer.write("Multiplicity, Michael Keaton, Andie MacDowell, Reading Entertainment,20\r\n");
            writer.write("Pulp Fiction, Quentin Tarantino, Uma Thurman, Reading Entertainment,108\r\n");
            writer.write("Raiders of the Lost Ark, Frank Marshall, Karen Allen, Elizabeth City Centre,242\r\n");
            writer.write("Saving Private ryan, Tom Hanks, Vin Diesel, Elizabeth City Centre,178\r\n");
            writer.write("Schindler's List, Liam Neeson, Embeth Davidtz, Harbour Town Cinema,96\r\n");
            writer.write("Scream, David Arquette, Neve Campbell, City Myer Cinema,103\r\n");
            writer.write("Speed 2, Jason Patric, Sandra Bullock, Sunny Bank Cinema,48\r\n");
            writer.write("Terminator, Arnold Schwarzenegger, Linda Hamilton, Reading Entertainment,37\r\n");
            writer.write("Speed 2, Jason Patric, Sandra Bullock, Sunny Bank Cinema,48\r\n");
            writer.write("President, Michael Douglas, Annette Bening, Harbour Town Cinema,65\r\n");
            writer.write("The Fifth Element, Bruce Willis, Milla Jovovich, City Myer Cinema,64\r\n");
            writer.write("The Game, Sean Penn, Deborah Kara Unger, Sunny Bank Cinema,48\r\n");
            writer.write("The Iron Mask, Leonardo DiCaprio, Jeremy Irons, Harbour Town Cinema,57\r\n");
            writer.write("Titanic, Billy Zane, Kate Winslet, City Myer Cinema,601\r\n");
            writer.write("True Lies, Bill Paxton, Jamie Lee Curtis, Sunny Bank Cinema,146\r\n");
            writer.write("Volcano, Don Cheadle, Anne Heche, Reading Entertainment,48\r\n");
            writer.write("Kingsman, Taron Egerton, Halle Berry, Harbour Town Cinema,10\r\n");
            writer.write("The LEGO, Jackie Chan, Michael Pe a, City Myer Cinema,34\r\n");
            writer.write("The Emoji Movie, T.J. Miller, Patrick Stewart, Sunny Bank Cinema,12\r\n");
            writer.write("Cpt Underpants, Kevin Hart, Ed Helms, Reading Entertainment,11\r\n");
            writer.write("Victoria & Abdul, Ali Fazal, Judi Dench, Sunny Bank Cinema,11\r\n");
            writer.write("Assassin, Dylan O'Brien, Sanaa Lathan, Elizabeth City Centre,20\r\n");
            writer.write("Mother!, Javier Bardem, Jennifer Lawrence, Sunny Bank Cinema,45\r\n");
            writer.write("Bodyguard, Ryan Reynolds, Salma Hayek, Reading Entertainment,21\r\n");
            writer.write("Patti Cake$, Mamoudou Athie, Danielle Macdonald, Sunny Bank Cinema,8\r\n");
            writer.write("La La Land, Ryan Gosling, Emma Stone, City Myer Cinema,7\r\n");
            writer.write("Zootopia, Jason Bateman, Shakira, Sunny Bank Cinema,15\r\n");
            writer.write("Arrival, Jeremy Renner, Amy Adams, City Myer Cinema,14\r\n");
            writer.write("Deadpool, Ryan Reynolds, Morena Baccarin, Sunny Bank Cinema,15\r\n");
            writer.write("Aliens, James Cameron, Carrie Henn, Reading Entertainment,82\r\n");
            writer.write("Armageddon, Bruce Willis, Liv Tyler, Reading Entertainment,194\r\n");
            writer.write("As Good As It Gets, Jack Nicholson, Helen Hunt, Elizabeth City Centre,148\r\n");
            writer.write("Braveheart, Mel Gibson, Sophie Marceau, Elizabeth City Centre,76\r\n");
            writer.write("Chasing Amy, Kevin Smith, Joey Lauren Adams, Reading Entertainment,12\r\n");
            writer.write("Contact, Matthew McConaughey, Jodie Foster, Reading Entertainment,101\r\n");
            writer.write("Dante's Peak, Pierce Brosnan, Linda Hamilton, Elizabeth City Centre,67\r\n");
            writer.write("Deep Impact, Morgan Freeman, T a Leoni, Elizabeth City Centre,140\r\n");
            writer.write("Executive Decision, Steven Seagal, Halle Berry, Reading Entertainment,69\r\n");
            writer.close();
            JOptionPane.showMessageDialog(null,"File successfully generated","",
            JOptionPane.INFORMATION_MESSAGE); // message when file has been created
            ta.setText(String.format("  =============================================================================================================================================\n"
                        +"File MovieData.txt has been created\n"+
                            "Please =>Read data<= again!!\n"+
                        "=============================================================================================================================================")); 
        }catch (IOException eee) 
        {
            eee.printStackTrace();// when there's system error
        }
    }
    }
}   
private void Display()
{ // start of display method
    String outputMsg =""; //assign null variable to catch data outside the for loop
    for (int i = 0; i < myList.size();i++)
    {  
        //assign value as string 
        outputMsg = outputMsg + myList.get(i).toString()+"\n";
    }
    ta.setText("\tMovie Name\t\tLead Actor Name\tLead Actress Name\tTheatre Name\t\tTicket Price\n"
    +"=============================================================================================================================================\n"
    +outputMsg // call the value in text area
    +"=============================================================================================================================================");              
   } // end of display method
private void saveFile()
{ //save file method
    try
    {   // assign object for printwriter and declare filename and destination where the file to save 
        try (PrintWriter out = new PrintWriter("NewMovieData.txt")) 
        {   //lamda expression for each data
            myList.forEach((myList1) -> 
            {
                //initialized value to print in text file (movie name, movie theatre name and price)
                out.printf("%s, %s, %s%n", myList1.getmovieName(), myList1.gettheatreName(), myList1.getticketPrice());
            });
            out.printf("%n%nThere are "); //additional print text
            String movieCnt = myList.size()+" entries of movies"; // for movie counts
            out.printf("%s",movieCnt); // //additional print text
            out.close();//close file
            moiveCntLoc = "There are "+myList.size()+" movies has been captured."; //this message is for user message
        }
            if (myList.isEmpty())
                JOptionPane.showMessageDialog(null,"No data found!\nYou must read data first!","",
                JOptionPane.INFORMATION_MESSAGE); //when there is not record found
            else 
                JOptionPane.showMessageDialog(null," Data saved successfully!\n"+moiveCntLoc,"",
                JOptionPane.INFORMATION_MESSAGE); //message after saving file
    }catch (Exception e)
    {
        ta.setText(String.format("=============================================================================================================================================\n"
        +"Saving file failed.\n"+
        "You must read data first!\n"+
        "============================================================================================================================================="));
        JOptionPane.showMessageDialog(null,"No data found!","",
        JOptionPane.INFORMATION_MESSAGE); //when there is not record found
    }
} //end of save new file method
private void listMovie() 
{// list movie method
    try
    {   if (myList.isEmpty())
        {   
            ta.setText(String.format("Your search result is:\tMovie Name\t\tLead Actor Name\tLead Actress Name\tTheatre Name\t\tTicket Price\n"
            +"============================================================================================================================================="
            +"Nothing to be sort no record found!!!\n"+
            "=============================================================================================================================================")); 
            JOptionPane.showMessageDialog(null,"You must read data first!!!","",
            JOptionPane.INFORMATION_MESSAGE);
        }else{
        Display(); // list the movie data from text file
        }
    }catch (Exception ex)
    {
        ta.setText(String.format("\n Movie Name\t\tLead Actor Name\tLead Actress Name\tTheatre Name\t\tTicket Price\n"
        +"  =============================================================================================================================================\n"
        +"Nothing to be list no record found!!!\n"+
        "============================================================================================================================================="));
        JOptionPane.showMessageDialog(null,"You must read data first!!!","",
        JOptionPane.INFORMATION_MESSAGE); // if not file fouond
    }
}//end of listMovie method
private void sortMovie()
{   
    divide(0, myList.size()-1); // mearge sorting 
}
private void divide(int startIndex,int endIndex)
{
//Breakdown your list to single element
    if(startIndex<endIndex && (endIndex-startIndex)>=1)
    {
        int mid = (endIndex + startIndex)/2;
        divide(startIndex, mid);
        divide(mid+1, endIndex);                     
        //merging Sorted array produce above into one sorted array
        merger(startIndex,mid,endIndex);            
    }       
} 
private void merger(int startIndex,int midIndex,int endIndex)
{
    //merged array to be sorted array Array[i-midIndex] , Array[(midIndex+1)-endIndex]
    ArrayList<Movie> mergedSortedArray = new ArrayList<>();  
    int leftIndex = startIndex;
    int rightIndex = midIndex+1;  
    while(leftIndex<=midIndex && rightIndex<=endIndex){
    if(myList.get(leftIndex).compareTo(myList.get(rightIndex))<= -1)
    {
        mergedSortedArray.add(myList.get(leftIndex));
        leftIndex++; 
    }else
    {
        mergedSortedArray.add(myList.get(rightIndex));
        rightIndex++;
    }
    }         
    //while loop will  to execute if meet the condition
    while(leftIndex<=midIndex)
    {
        mergedSortedArray.add(myList.get(leftIndex));
        leftIndex++;
    }
    //while loop will  to execute if meet the condition
    while(rightIndex<=endIndex)
    {
        mergedSortedArray.add(myList.get(rightIndex));
        rightIndex++;
    }   
    int i = 0;
    int j = startIndex;
    //Setting sorted array to original one
    while(i<mergedSortedArray.size())
    {
    myList.set(j, mergedSortedArray.get(i++));
            j++; //merge sort the array
    }
    }
private void sortDisMovie()
{//sort method
    try
    {
        if (myList.isEmpty())
        {   
            ta.setText(String.format("Your search result is:\tMovie Name\t\tLead Actor Name\tLead Actress Name\tTheatre Name\t\tTicket Price\n"
            +"============================================================================================================================================="
            +"Nothing to be sort no record found!!!\n"+
            "=============================================================================================================================================")); 
            JOptionPane.showMessageDialog(null,"You must read data first!!!","",
            JOptionPane.INFORMATION_MESSAGE);
        }else{ 
            sortMovie(); // call sort method
            Display();  // call display
        }
    }catch (Exception ex)
    {
        ta.setText(String.format("Your search result is:\tMovie Name\t\tLead Actor Name\tLead Actress Name\tTheatre Name\t\tTicket Price\n"
        +"============================================================================================================================================="
        +"Nothing to be sort no record found!!!\n"+
         "=============================================================================================================================================")); 
        JOptionPane.showMessageDialog(null,"You must read data first!!!","",
            JOptionPane.INFORMATION_MESSAGE);
    }
   
}//end of sortDisMovie
private void searchMovie() 
{// start of search method
    try
    { 
        String input = JOptionPane.showInputDialog(frame, "Enter movie ticket price for searching: ", "Input", JOptionPane.QUESTION_MESSAGE);
        if (input == null)//if in put is null
        {
            JOptionPane.showMessageDialog(null,"No input entered","",
            JOptionPane.INFORMATION_MESSAGE);
            searchList = String.format("No record found");
            inputCompare = input;
        }else if (input != null) //if input is not null
        {
            double tp2 = Double.parseDouble(input); //convert string to double
            Predicate<Movie> findTp =
            tpValue -> (tp2 == tpValue.getticketPrice()); // comparison to find when tp2 is equal to ticket price
            if (tp2 < 2) //if input is less than to 2
            {
            JOptionPane.showMessageDialog(null,"Not a valid input!","",
            JOptionPane.INFORMATION_MESSAGE);
            searchList = String.format("No record found");
            }else
            {
            searchList = String.format("%s%n", 
            myList.stream()//get the collection of mylist as source
                  .filter(findTp) // fliter input
                  .findFirst() // find firt data equal to input
                  .get()); // get the value otherise throw exception
            }
        }
            ta.append("\tMovie Name\t\tLead Actor Name\tLead Actress Name\tTheatre Name\t\tTicket Price\n"
            +"=============================================================================================================================================\n"
            +searchList+"\n"
            +"=============================================================================================================================================");     
    }catch (Exception ex)
    {
        ta.setText(String.format("Your search result is:\n Movie Name\t\tLead Actor Name\tLead Actress Name\tTheatre Name\t\tTicket Price\n"
        +"=============================================================================================================================================\n"
        +"No record found!!!\n"+
        "============================================================================================================================================="));
        JOptionPane.showMessageDialog(null,"Search is not in the record!","",
        JOptionPane.INFORMATION_MESSAGE); // if record not found
    }
}//end of method seachMovie
private void searchMovieC()
{
 if (myList.isEmpty())
        {   
            ta.setText(String.format("Your search result is:\tMovie Name\t\tLead Actor Name\tLead Actress Name\tTheatre Name\t\tTicket Price\n"
            +"============================================================================================================================================="
            +"Nothing to be sort no record found!!!\n"+
            "=============================================================================================================================================")); 
            JOptionPane.showMessageDialog(null,"You must read data first!!!","",
            JOptionPane.INFORMATION_MESSAGE);
        }else{
        searchMovie();
}
}
private void distributorStream()
{ //distributor data
    try
    {   //array list for distributor details (namae, address and phone number) 
        ArrayList<String> listDistributor=new ArrayList<>();
        listDistributor.add("\tGerri Weinmann\t 8 Di Loreto Pass Keysborough 3173 VIC\t\t 1179739939");
        listDistributor.add("\tIngmar Symondson\t 28 Burrows Parkway Carlton North SA 5000\t\t 1741313287");
        listDistributor.add("\tChristin Klampk\t 61941 Scofield Parkway Docklands QLD 4133\t\t 1906492733");
        listDistributor.add("\tIzaak Gadie\t\t 7083 Kenwood Court Flemington VIC 4285\t\t 1234567778");
        listDistributor.add("\tPennie Withey\t\t 01071 Delaware Trail Keysborough SA 3173\t\t 1234667675");
        listDistributor.add("\tIsadora Matteotti\t 60 Browning Crossing Kensington VIC 4270\t\t 1346567766");
        listDistributor.add("\tLoy Cumberland\t 507 Hayes Lane East Perth WA 6107\t\t 1234555656");
        listDistributor.add("\tElla Joselovitch\t\t 07154 Cardinal Lane Parklea SA 5112\t\t 1234566665");
        listDistributor.add("\tGoldina Yuranovev\t 08701 6th Circle Glebe SA 6084\t\t 1133445565");
        listDistributor.forEach((obj1) -> { // for loop for each data
        ta.append(obj1+"\n"); // set object to string as text in text area 
        });
        ta.setText("\tDistributor Name\tDistributor Address\t\t\tPhone Number\n"+
            "=============================================================================================================================================\n"+
            ta.getText()+ // get the text from text area append string
            "============================================================================================================================================="
            );
    }catch (Exception ex)
    {
         ta.setText("\tDistributor Name\tDistributor Address\t\t\tPhone Number\n"+
            "=============================================================================================================================================\n"+
            "No record found"+
            "============================================================================================================================================="
            );// when there is system error
    }
}
private void PanelComp()
{// jpanel component
    JPanel p = new JPanel();
    p.setBorder(new TitledBorder(new EtchedBorder(), "Display Area"));
    p.add(scroll);
    add(p);      
}
public static void main(String[] args) 
{
    //Frame attributes
    MovieApplication ma = new MovieApplication();
    ma.setVisible(true); // set the visibility to true
    ma.setSize(1100,550); // set the size of the window
    ma.PanelComp(); // call the panels components
    ma.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // show close button to exit the program
}//end of main method
}//end of class MovieApplication
