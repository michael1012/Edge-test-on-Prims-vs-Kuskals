package project;

//Test File To show how we can measure time and space of algorithm 
//Code referenced from the following pages: 
//http://stackoverflow.com/questions/1770010/how-do-i-measure-time-elapsed-in-java
//http://www.vogella.com/tutorials/JavaPerformance/article.html

//Simple example just creates a relatively large array of numbers and sorts it

//Memory numbers seem a bit high to me, might have to test more later. 

import java.util.*;

public class TimeSpace
{ 
	// Note: 1000000000 nanoseconds is 1 second
	private ArrayList< ArrayList<Long> > times;
	private ArrayList< ArrayList<Long> > spaces;
	
	private long start; 
	private long stop; 
	private Runtime rt; 
	
	public TimeSpace()
	{
		  start = 0;
		  stop = 0;
		  
		  times = new ArrayList< ArrayList<Long> >();
		  spaces = new ArrayList< ArrayList<Long> >();
		  // Get the Java runtime
		  rt = Runtime.getRuntime();
		   
	}
	  
	public void startSpaceMeasurment()
	{
		   rt.gc();
	}
	public void stopSpaceMeasurment()
	{
		   rt.gc(); 
	}
	public long getUsedMemory()
	{
		   return ( getTotalMemory() - getFreeMemory() );
	}
	public long getTotalMemory()
	{
		   return rt.totalMemory();
	}
	public long getFreeMemory()
	{
		   return rt.freeMemory();
	}
	
	public void startClock()
	{
		   start = System.nanoTime();
	}
	public void stopClock()
	{
		   stop = System.nanoTime();
	}
	
	public long getDurationNano()
	{
		   return (stop - start);
	}
	public double getDurationSeconds()
	{
		   return ((stop - start) / 1000000000.0);
	}
	
	public void recordTime( int trial )
	{
		if (times.size() > trial)
			times.get(trial).add( getDurationNano() );
		else
		{
			ArrayList<Long> temp = new ArrayList<Long>();
			temp.add( getDurationNano() );
			times.add( temp );
		}
		
		start = 0; 
		stop = 0; 
	}
	
	public void recordSpace( int trial )
	{
		if (spaces.size() > trial)
			spaces.get(trial).add( getUsedMemory() );
		else
		{
			ArrayList<Long> temp = new ArrayList<Long>();
			temp.add( getUsedMemory() );
			spaces.add( temp );
		}
		start = 0; 
		stop = 0; 
	}
	
	public ArrayList<Long> getTimes()
	{
		ArrayList<Long> averageTimes = new ArrayList<Long>();
		for (int i = 0; i < times.size(); i++)
		{
			averageTimes.add( ArrayListAverage( times.get(i) ) );
		}
		return averageTimes;
	}
	
	public ArrayList<Double> getTimesSeconds()
	{
		ArrayList<Long> temp = getTimes();
		ArrayList<Double> timeSec = new ArrayList<Double>();
		for (int i = 0; i < temp.size(); i++)
		{
			timeSec.add( temp.get(i) / 1000000000.0 );
		}		
		return timeSec;
	}
	
	public ArrayList<Long> getSpaces()
	{
		ArrayList<Long> averageSpaces = new ArrayList<Long>();
		for (int i = 0; i < times.size(); i++)
		{
			averageSpaces.add( ArrayListAverage( spaces.get(i) ) );
		}
		return averageSpaces;
	}
	
	public long ArrayListAverage( ArrayList<Long> ary)
	{
		long average = 0; 
		for (int i = 0; i < ary.size(); i++)
		{
			average += ary.get(i);
		}
		
		return ( average / ary.size() );
	}
	
	// Given an ArrayList, returns SD of array elements
	public long ArrayListStardDev( ArrayList<Long> ary)
	{
		long mean = ArrayListAverage(ary); 
		ArrayList<Long> temp = new ArrayList<Long>();
		for (int i = 0; i < ary.size(); i++)
		{
			temp.add( (long) Math.pow( (ary.get(i) - mean) , 2));
		}
		
		return ( ArrayListAverage(temp) );
	}
}



