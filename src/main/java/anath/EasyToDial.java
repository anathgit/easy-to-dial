package anath;

import java.util.HashMap;
import java.util.Map;


/**
 * This class defines function that would allow to deteremine if a phone number is easy to dial or not
 * The input keypad is represented as 2-D matrix[4][3] with digits from 0 to 9 and last row has been filled with -1
 * for column index 1 and 2
 * The Time complexity of the function is linear and bounded by the length of the input since the inner for loop
 * inside that looks for neighbouring elements and is constant and does not change with the size of the input
 * so Time Complexity = O(n)
 * Space Complexity = O(n)
 *
 * Assumptions
 * All inputs are assumed to be valid and hyphen delimited.
 */

public class EasyToDial {

    int[][] keypad = {{1,2,3},
                      {4,5,6},
                      {7,8,9},
                      {0,-1,-1}};

    Map<Integer,int[]> lookUpMap;

    public EasyToDial(){
        lookUpMap = new HashMap<>();

        for(int i=0; i < keypad.length;i++){
            for(int j=0; j < keypad[0].length;j++){
                lookUpMap.put(keypad[i][j],new int[]{i,j});
            }
        }
    }


    public static void main(String[] args) {
        EasyToDial easyToDial = new EasyToDial();
        System.out.println(easyToDial.isEasyToDial("254-7096"));
        System.out.println(easyToDial.isEasyToDial("554-7521"));
        System.out.println(easyToDial.isEasyToDial("280-6547"));
        System.out.println(easyToDial.isEasyToDial("355-8123"));
        System.out.println(easyToDial.isEasyToDial("753-9586"));
        System.out.println(easyToDial.isEasyToDial("596-8421"));
    }

    private String isEasyToDial(final String s) {

        String input = s.replace("-","");

        //Always 1 less of the input's length
        int neighbourCountToBeMatched = input.length()-1;

        //All co-ordinates one hop away from current co-ordinate and the self
        int[][] dirs = {{1,0},{-1,0},{0,-1},{0,1},{-1,-1},{1,1},{1,-1},{-1,1},{0,0}};

        char[] charArray = input.toCharArray();

        for(int i=0; i < charArray.length -1; i++){

            int current = charArray[i] - '0';
            int  next = charArray[i+1] - '0';

            //Since 0 is assumed to be neighbour of all the elements above it - inferred from the first example that
            // should return "Easy to dial"
            if(current == 0 && (next ==7 || next == 8 || next == 9)){
                neighbourCountToBeMatched--;
                continue;
            }

            int[] currentCoordinate = lookUpMap.get(current);

            for(int[] dir : dirs){
                int newX = dir[0]+currentCoordinate[0];
                int newY = dir[1]+currentCoordinate[1];

                if(newX < 0 || newX >= keypad.length || newY < 0 || newY >= keypad[0].length){
                    continue;
                }

                int possibleNeighbour = keypad[newX][newY];

                if(next == possibleNeighbour){
                    neighbourCountToBeMatched--;
                }
            }

        }

        return neighbourCountToBeMatched == 0 ? "Easy to dial" : "Not easy to dial";
    }
}
