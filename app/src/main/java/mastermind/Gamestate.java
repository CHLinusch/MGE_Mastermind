package mastermind;

public class Gamestate {
    private final int[][] placedPegs;
    private final boolean repeatable;

    public Gamestate(String saveState, String rep){
        String[] boxStates = saveState.split(" ");
        placedPegs = new int[][]{{6,6,6,6},{6,6,6,6},{6,6,6,6},{6,6,6,6},
                {6,6,6,6},{6,6,6,6},{6,6,6,6},{6,6,6,6},{6,6,6,6}};
        if (boxStates.length == 9){
            for (int i = 0; i < boxStates.length; i++){
                for (int j = 0; j < boxStates[i].length(); j++){
                    placedPegs[i][j] = Character.getNumericValue(boxStates[i].charAt(j));
                }
            }
        }

        repeatable = "true".equals(rep);
    }


    public int[][] getPlacedPegs() {
        return placedPegs;
    }

    public boolean isRepeatable() {
        return repeatable;
    }


}
