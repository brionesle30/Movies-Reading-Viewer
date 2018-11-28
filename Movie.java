/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Properties;

/**
 *
 * @author 12051033
 */
public class Movie implements Comparable<Movie> 
{
    String movieName;
    String leadActorName;
    String leadActressName;
    String theatreName;
    double ticketPrice;

    public Movie(String mName, String lActorName, String lActressName, String tname, double tPrice)
    {
        this.movieName = mName;
        this.leadActorName = lActorName;
        this.leadActressName = lActressName;
        this.theatreName = tname;
        this.ticketPrice = tPrice;
    }
    //mutator methods
   public void setmovieName(String mn)
   {
       movieName = mn;
   }
   public void setleadActorName(String ban)
   {
       leadActorName = ban;
   }
   public void setleadActressName(String gan)
   {
       leadActressName = gan;
   }
   public void settheatreName(String tm)
   {
       theatreName = tm;
   }
   public void setticketPrice(double tp)
   {
       ticketPrice = tp;        
   }
   //accessor methods
   public String getmovieName()
   {
    return movieName;
   }
   public String getleadActorName()
   {
    return leadActorName;
   }
   public String getleadActressName()
   {
    return leadActressName;
   }
   public String gettheatreName()
   {
    return theatreName;
   }
   public double getticketPrice()
   {
    return ticketPrice;
   }
    @Override
    public String toString()
    {
        return String.format("\t%-40s", movieName)
                            +String.format("\t%-26s",leadActorName)
                            +String.format("\t%-30s",leadActressName)
                            +String.format("\t%-23s",theatreName)
                            +String.format("\t%-5s",ticketPrice);
    }
    @Override
//get string to int as index of the element for merge comparson
 public int compareTo(Movie movie)
    {
    return getmovieName().compareTo(movie.getmovieName());
    }
}
