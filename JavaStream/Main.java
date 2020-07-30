//package com.company;
import java.util.*;
import java.util.stream.*;

import org.json.simple.*;
import java.security.SecureRandom;

public class Main {


    static void Example1(){

        //Create a String array of colors
            //input 3 colors
        String[] colors1 = {"red", "blue", "green"};

        //Create an ArrayList of Strings
        ArrayList<String> colors2 = new ArrayList<String>();


        //using an enhanced for loop
            //add all colors of String array to ArrayList
        for(String item : colors1){

            colors2.add(item);
        }

        Iterator<String> colorsIterator = colors2.iterator();

        //ask the user for a color (using Scanner)

        for (String color: colors2){

            System.out.println(color);
        }

        Scanner input = new Scanner(System.in);
        System.out.println("Enter a color: ");
        String userColor = input.nextLine();

        boolean userContains = colors2.contains(userColor);


        if(userContains){
            colors2.remove(userColor);
        }

        for (String color: colors2){

            System.out.println(color);
        }
        //determine if colors2 CONTAINS user color
        //if it does, REMOVE it

        System.out.println("Using List Iterator");

        for(int i = 0; i < colors2.size() ; i++) {
            if (colorsIterator.hasNext()) {
                System.out.println(colorsIterator.next());
            }
        }


    }
/*
Task 1
Create an integer array of 4 numbers
Create an ArrayList of Integer based on integer array

Ask the user for a number

Using the Iterator,
    Cycle thru all items in ArrayList. Stop at number if found
 */
static void Example2(){

    int[] top4 = {1, 3, 8, 14};

    ArrayList<Integer> nums = new ArrayList<Integer>();

    for(int current : top4){

        nums.add(current);
    }

    Scanner input = new Scanner(System.in);

    System.out.println("Enter a number");

    try{
        int userNumber = input.nextInt();
        ListIterator<Integer> iter = nums.listIterator();

        for(int i = 0; i < nums.size() ; i++){
            if(iter.hasNext()){
                if(iter.next().equals(userNumber)){
                    System.out.printf("%d was found!", userNumber);
                    break;
                }
            }
        }
    }
    catch (Exception e){
        System.err.println(e);
    }
}

static void Example3(){

    //create array of doubles
    double[] nums = {7.7, 5.5, 1.1};
    //create a LinkedList from the array of doubles

    LinkedList<Double> linkedList = new LinkedList<Double>();

    for(double current : nums){

        linkedList.add(current);
    }

    //Add a double to the start of linkedlist

    linkedList.addFirst(3.3);
    //Add a double to the end of linkedlist
    linkedList.addLast(-2.2);

    //create a listiterator from linkedlist
    ListIterator<Double> listIterator = linkedList.listIterator();

    System.out.println(listIterator.hasNext());
    System.out.println(listIterator.next());
    System.out.println(listIterator.nextIndex());

    System.out.println(listIterator.previous());


}
static void Example4(){

    //Create two integer lists
    LinkedList<Integer> first = new LinkedList<Integer>();
    LinkedList<Integer> second = new LinkedList<Integer>();

    first.add(123);
    first.add(456);
    first.addLast(789);

    second.add(234);
    second.add(567);
    second.add(789);

    System.out.println(Collections.disjoint(first, second));
    System.out.println(Collections.min(first));
    System.out.println(Collections.frequency(first, 123));

}


static void Example5(){

    PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

    pq.offer(12);
    pq.offer(1);
    pq.offer(120);
    pq.offer(-12);

    //using a loop, output all the values of the priority queue
    Iterator<Integer> itr = pq.iterator();

    while(itr.hasNext()){
        System.out.println(itr.next());
    }

    System.out.println();
    while(pq.size() > 0){

        System.out.println(pq.peek());
        pq.poll();
    }

}
static void Example6(){


    //Create a TreeSet of Doubles
    //of values from 1 to 10
    TreeSet<Double> treeSet = new TreeSet<Double>();
    treeSet.add(1d);
    treeSet.add(2d);
    treeSet.add(3d);
    treeSet.add(4d);
    treeSet.add(5d);
    treeSet.add(6d);
    treeSet.add(7d);
    treeSet.add(8d);
    treeSet.add(9d);
    treeSet.add(10d);

    //Output to screen

        //All values >=7

        SortedSet<Double> ss = treeSet.tailSet(7d);
            //use enhanced for loop

    for(double value : ss){
            System.out.println(value);

        }
        //All values <4
            //use enhanced for loop
        //first value
    System.out.println(treeSet.first());
        //last value

}

static void Example7(){
/*
Create a Map that will contain a integer as the key
and any data type as the value

Ask the user 3 times for a value
Insert the value at a key.

Using a loop, output the key-value pair of the Map

 */
    TreeMap<String, String>  treeMap = new TreeMap<String, String>();
    treeMap.put("firstname", "COMP");
    treeMap.put("lastname", "1011");
    System.out.println(treeMap.get("firstname"));
    System.out.println(treeMap.containsKey("lastname"));
    System.out.println(treeMap.containsValue("COMP"));
    Set<String> keys = treeMap.keySet();
    for(String item : keys){
        System.out.println(item);
        System.out.println(  treeMap.get(item)  );
    }

}

static void Example8(){

//Create a JSON object of a series of name/values

    JSONObject json = new JSONObject();
    json.put("firstname", "comp1011");
    json.put("lastname", "Java");
    json.put(1, true);
    System.out.println(json.toJSONString());
    System.out.println(json.toString());

    String toSend1 = json.toJSONString();

    JSONArray series = new JSONArray();
    series.add(12);
    series.add(true);
    series.add("Hello");

    System.out.println(series.toJSONString());
    System.out.println(series.toString());

    String toSend2 = series.toJSONString();

    System.out.println();
    System.out.println("Example 9 Called");
    Example9(toSend1);
}
static void Example9(String jsonString){

    System.out.println(jsonString);

    Object jsonReceivedObject = JSONValue.parse(jsonString);
    System.out.println(jsonReceivedObject);

    JSONObject jsonObject = (JSONObject)jsonReceivedObject;

    System.out.println(jsonObject.get("firstname"));
    jsonObject.put("firstname", "New Value");
    System.out.println(jsonObject.get("firstname"));


}
/*
Create Example10
    accepts a string json value
    convert the json string into a json array
    output the second index
    change the first index
    output the first index

 */

static void Example11(){

    Double avg = IntStream.rangeClosed(1, 10).average().orElse(0);

    IntStream.range(1, 11).min().orElse(0);


    /*
        Double avg = IntStream.rangeClosed(1, 10).average().orElse(0);
        using procedural programming
     */
    double sum1 = 0;
    double numValue = 0;
    double avg1;
    for(int i = 1; i < 11; i++){
        sum1 += i;
        numValue++;
    }

    if(numValue != 0){
        avg1 = sum1 / numValue;
    }
    else{
        avg1 = 0;
    }


    System.out.println(avg);
}
public static void Example12(){


    Integer[] intArray = {11, 2, 53, 4, 5 };
    Stream<Integer> integerStream = Stream.of(intArray);
    Optional randomValue = integerStream.findFirst();
    System.out.println(randomValue);



}
static void Example13(){

    int finalValue = IntStream.range(1, 11).limit(3).sum();

    System.out.println(finalValue);

}
    static void Example14(){

        int finalValue = IntStream.range(1, 11).filter( x -> x % 2 == 1 ).sum();

        System.out.println(finalValue);

    }


    static void Example15(){

        int finalValue = IntStream.range(1, 6)
                .map( x -> { return x % 2 == 0 ? x *= 2 : x; } ).sum();

        /*
        1   2   3   4   5

        1 + 4 +3 + 8 + 5

         */

        System.out.println(finalValue);

    }

    static void Example16(){

        /*
                Create a list of names
                Turn this list into a stream
                Output names that start with vowel
         */

        ArrayList<String> namesList = new ArrayList<>();
        namesList.add("Ben");
        namesList.add("Anthony");
        namesList.add("ed");
        namesList.add("John");

        namesList.stream().map( letter -> letter.toLowerCase())
                .filter( (String currentName) -> { return  (currentName.charAt(0) == 'a'
                        || currentName.charAt(0) == 'e' || currentName.charAt(0) == 'i' ||
                        currentName.charAt(0) == 'o' || currentName.charAt(0) == 'u');   })
        .forEach(System.out::println);


    }
    static void Example17(){


        SecureRandom sran = new SecureRandom();
        IntSummaryStatistics stats = sran.ints(10, 1, 100).
        filter(x -> x > 20).summaryStatistics();

        System.out.println(stats.getMax());
        System.out.println(stats.getAverage());

    }
    static void Example18(){

     String text = new SecureRandom().ints(5, 1, 10)
             .mapToObj(String::valueOf).collect(Collectors.joining(", "));

     System.out.println(text);
    }

    static void Example19(){


        int value_1, value_2;

        value_1 = IntStream.rangeClosed(1, 10).reduce( (x , y) -> x * y).orElse(0);
        value_2 = IntStream.rangeClosed(1, 10).reduce( 0, (x , y) -> x * y);

    }

    static void Example20(){

        ArrayList<String> namesList = new ArrayList<>();
        namesList.add("Anthony");
        namesList.add("ed");
        namesList.add("Ben");
        namesList.add("John");

        String finalCandidate = namesList.stream().filter( x -> x.length() <5).
                map(x -> x.toUpperCase()).limit(1).collect(Collectors.joining());

        System.out.println(finalCandidate);

    }

    public static void main(String[] args) {
	// write your code here
        Example20();
    }
}
