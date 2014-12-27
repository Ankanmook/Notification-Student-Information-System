package edu.rit.notification.poller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Timer;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import edu.rit.notification.binding.PollingLoopback;
import edu.rit.notification.entity.ClassSchedule;

@Service
public class OneHourPoller implements Pollable<ClassSchedule> {
	private Queue<ClassSchedule> queue;
	private PollingLoopback loopback;
	TreeMap<String, List<ClassSchedule>> timeTable;

	Timer timer = new Timer();

	public OneHourPoller() {
		queue = new LinkedList<ClassSchedule>();
		/*
		 * timer.schedule(new TimerTask() {
		 * 
		 * @Override public void run() { poll(); } }, 20*1000);
		 */
	}

	
	public synchronized void poll() {
		// add polling to this method to retrieve class due in one hour
		System.out.println("Once hour Poller Poll() is called");
		if(timeTable==null){
			System.out.println("TimeTable is empty...");
			return;
		}
		Calendar cal = Calendar.getInstance();
		Date now = cal.getTime();

		cal.add(Calendar.MINUTE, 5);
		Date in15 = cal.getTime();
		String now_24 = getMilitaryTime(now);
		String in15_24 = getMilitaryTime(in15);
		//String first = timeTable.firstKey();

		String nextTime = timeTable.higherKey(now_24);

		//System.out.println("FirstKey: " + first + " floorKey is: " + timeTable.floorKey(now_24));
		System.out.println("NextKey: " + nextTime + " Next 15 mins is: "
				+ in15_24);

		// if (first.equals(timeTable.floorKey(now_24))) {
		if (nextTime != null)
			System.out.println("nextTime.compareTo(" + in15_24 + ") is "
					+ nextTime.compareTo(in15_24));

		if (nextTime != null && nextTime.compareTo(in15_24) == 0) { //nextTime.compareTo(in15_24) <= 0
			System.out.println("match found...");
			System.out.println(nextTime + " is within " + now_24 + " and "
					+ in15_24);
			Entry<String, List<ClassSchedule>> entry = 
					timeTable.higherEntry(now_24);
					//.pollFirstEntry();
			List<ClassSchedule> classSchedule = entry.getValue();

			
			System.out.println("********FirstEntry: " + entry
					+ " sending size: " + classSchedule.size());

			loopback.putScheduleList(classSchedule);
			// } else if (first.compareTo(now24) == -1) {// the next class time
			// (first)
			// is less than the current
			// time (passed)
			// System.out.println("Class time is passed. Now24:"+now24 );
			// System.out.println("Removing '"+first+ "' from the list...");
			// timeTable.pollFirstEntry(); //remove the first entry
			// System.out.println("Next time is "+timeTable.firstKey());

		}

		/*
		 * while(!queue.isEmpty()){
		 * System.out.println("Sending to perminute queue"); ClassSchedule c =
		 * queue.poll(); System.out.println("loopback interface is " +
		 * loopback); loopback.putScheduleEvent(c); }
		 */
	}

	@Override
	public synchronized void receive(List<ClassSchedule> schedules) {
		timeTable = new TreeMap<String, List<ClassSchedule>>();

		List<ClassSchedule> list;

		System.out.println("OneHourPoller: Received class schedule list...");
		for (ClassSchedule c : schedules) {
			System.out.print("Enqueing One day queue...");
			String mt = null;
			try {
				mt = getMilitaryTime(c.getStartTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(mt);
			list = timeTable.get(mt);
			if (list == null) {
				list = new ArrayList<ClassSchedule>();
				list.add(c);
				timeTable.put(mt, list);
				
			} else {
				list.add(c);
			}
			// queue.offer(c);

		}

	}

	private String getMilitaryTime(String time) throws ParseException {
		SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
		Date date = timeFormat.parse(time);
		return getMilitaryTime(date);

	}

	private String getMilitaryTime(Date date) {
		Calendar calendar = GregorianCalendar.getInstance(); // creates a new
																// calendar
																// instance
		calendar.setTime(date); // assigns calendar to given date
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		String strHour = ((hour < 10 ? "0" : "") + hour)
				+ ((minute < 10 ? "0" : "") + minute);
		return strHour;
	}

	public PollingLoopback getLoopback() {
		return loopback;
	}

	public void setLoopback(PollingLoopback loopback) {
		this.loopback = loopback;
	}

}
