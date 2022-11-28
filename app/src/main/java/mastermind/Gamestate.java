package mastermind;

public class Gamestate {
    private int[][] placedPegs;
    private boolean repeatable;

    public Gamestate(String saveState, String rep){
        String[] boxStates = saveState.split(" ");
        if (boxStates.length != 9){
            placedPegs = new int[][]{{6,6,6,6},{6,6,6,6},{6,6,6,6},{6,6,6,6},
                    {6,6,6,6},{6,6,6,6},{6,6,6,6},{6,6,6,6},{6,6,6,6}};
        }
        else{
            for (int i = 0; i < boxStates.length; i++){
                  for (int j = 0; j < boxStates[i].length(); j++){
                       placedPegs[i][j] = Integer.valueOf(boxStates[i].charAt(j));
                  }
            }
        }
        if (rep == "true"){
            repeatable = true;
        }
        else{
            repeatable = false;
        }
    }


    public int[][] getPlacedPegs() {
        return placedPegs;
    }

    public boolean isRepeatable() {
        return repeatable;
    }


}
