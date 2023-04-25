package org.aoc.days.day4;
import org.aoc.filehandling.FileLoader;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Comparator.reverseOrder;

public class day4 {

    public static int part1(ArrayList<Event> events){
        HashMap<Integer, HashMap<Integer, Integer>> guardSleep = generateSleepMap(events);
        // find guard that slept the most
        Integer maxSleep = 0;
        Integer maxGuardId = null;
        Integer mostSleptAtMinute = null;
        for(Map.Entry<Integer, HashMap<Integer, Integer>> gEntry : guardSleep.entrySet()){
            // sum time slept
            int sum = 0;
            int tempMostSleptMinutes = 0;
            int tempMostSleptAtMinute = 0;
            for(Map.Entry<Integer, Integer> sEntry : gEntry.getValue().entrySet()){
                sum += sEntry.getValue();
                if(sEntry.getValue() > tempMostSleptMinutes){
                    tempMostSleptAtMinute = sEntry.getKey();
                    tempMostSleptMinutes = sEntry.getValue();
                }
            }
            if(sum > maxSleep){
                maxSleep = sum;
                maxGuardId = gEntry.getKey();
                mostSleptAtMinute = tempMostSleptAtMinute;
            }

        }
        assert maxGuardId != null & mostSleptAtMinute != null;
        return maxGuardId * mostSleptAtMinute;
    }

    private static HashMap<Integer, HashMap<Integer, Integer>> generateSleepMap(ArrayList<Event> events) {
        HashMap<Integer, HashMap<Integer, Integer>> guardSleep = new HashMap<>();
        Integer guardId = null;
        Integer sleepTime = null;
        Integer awakeTime;

        Pattern guardIdPattern = Pattern.compile("#(\\d+)");

        for(int index = 0; index < events.size(); index++){
            Event e = events.get(index);
            String eType = e.getEventType();

            if(eType.contains("Guard")){
                Matcher guardIdMatcher = guardIdPattern.matcher(eType);
                if(guardIdMatcher.find()){
                    guardId = Integer.parseInt(guardIdMatcher.group(1));
                }
                assert guardId != null;
                continue;
            }
            if(eType.contains("falls")){
                sleepTime = e.getEventTime().getMinute();
            }
            if(eType.contains("wakes")){
                awakeTime = e.getEventTime().getMinute();
                assert sleepTime != null;

                // calculate minutes slept
                if(guardSleep.containsKey(guardId)){
                    HashMap<Integer, Integer> sleepMap = guardSleep.get(guardId);
                    for(int mIndex = sleepTime; mIndex < awakeTime; mIndex++){
                        if(sleepMap.containsKey(mIndex)){
                            sleepMap.replace(mIndex, sleepMap.get(mIndex)+1);
                        }
                        else{
                            sleepMap.put(mIndex, 1);
                        }
                    }
                    guardSleep.replace(guardId, sleepMap);
                }
                else{
                    HashMap<Integer, Integer> sleepSchedule = new HashMap<>();
                    for(int mIndex = sleepTime; mIndex < awakeTime; mIndex++){
                        sleepSchedule.put(mIndex, 1);
                    }
                    guardSleep.put(guardId, sleepSchedule);
                }
                sleepTime = null;
            }
        }
        return guardSleep;
    }

    public static int part2(ArrayList<Event> events){
        HashMap<Integer, HashMap<Integer, Integer>> guardSleep = generateSleepMap(events);

        Integer guardIdMostSleptMinute = null;
        Integer mostSleptMinute = null;
        Integer minutesSlept = 0;
        for(Map.Entry<Integer, HashMap<Integer, Integer>> gEntry : guardSleep.entrySet()){
            for(Map.Entry<Integer, Integer> sEntry : gEntry.getValue().entrySet()){
                if(sEntry.getValue() > minutesSlept){
                    minutesSlept = sEntry.getValue();
                    mostSleptMinute = sEntry.getKey();
                    guardIdMostSleptMinute = gEntry.getKey();
                }
            }
        }
        assert guardIdMostSleptMinute != null & mostSleptMinute != null;
        return guardIdMostSleptMinute * mostSleptMinute;
    }

    private static int compare(Event other, Event other2) {
        if(other.getEventTime().isBefore(other2.getEventTime())){
            return -1;
        }
        if(other.getEventTime().isAfter(other2.getEventTime())){
            return 1;
        }
        return 0;
    }
    public static ArrayList<Event> buildEvents(String inputFileName){
        ArrayList<String> eventDateStrings = FileLoader.loader(inputFileName);

        ArrayList<Event> outEvents = new ArrayList<>();

        Pattern datePattern = Pattern.compile("\\d+-\\d+-\\d+");
        Pattern timePattern = Pattern.compile("\\d+:\\d+");
        Pattern eventTypePattern = Pattern.compile("((wakes|Guard|falls).*)");

        for(String s : eventDateStrings){
            // Extract date time

            String eventDate = null;
            String eventTime = null;
            String eventType = null;
            Matcher DMatcher = datePattern.matcher(s);
            Matcher TMatcher = timePattern.matcher(s);
            Matcher ETMatcher = eventTypePattern.matcher(s);

            if(DMatcher.find())
            {
                eventDate = DMatcher.group(0);
            }
            if(TMatcher.find())
            {
                eventTime = TMatcher.group(0);
            }
            if(ETMatcher.find())
            {
                eventType = ETMatcher.group(0);
            }
            assert eventDate!=null && eventType != null && eventTime != null;

            LocalDateTime eventDateTime = LocalDateTime.parse(eventDate+"T"+eventTime);
            outEvents.add(new Event(eventDateTime, eventType));

        }
        outEvents.sort(day4::compare);
        return outEvents;
    }
    public static void run(String inputFileName) {
        ArrayList<Event> events = buildEvents(inputFileName);
        System.out.println("Part 1 " + part1(events));
        System.out.println("Part 2 " + part2(events));
    }
}
