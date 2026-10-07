// first line

package app.helpers.general;

public class Levenshit {
    private static final int INSERT_COST = 4;
    private static final int DELETE_COST = 8;
    private static final int SUBSTITUTE_COST = 3;
    private static final int SPECIAL_SUBSTITUTE_COST = 1;

    private Levenshit(){}

        // how many operations do I need to do to change A into B?
    public static int levenstein(String found, String given){
        int substringScore = substringScore(found, given);
        if (substringScore != Integer.MIN_VALUE){
            return substringScore;
        }
        return 100 - distance(found, given, Integer.MAX_VALUE);
    }

    public static int scoreAboveThreshold(String found, String given, int threshold){
        int substringScore = substringScore(found, given);
        if (substringScore != Integer.MIN_VALUE){
            return substringScore > threshold ? substringScore : -1;
        }

        int maxCost = 99 - threshold;
        if (maxCost < 0){
            return -1;
        }
        long lengthDifference = (long) found.length() - given.length();
        long minimumLengthCost = lengthDifference > 0
            ? lengthDifference * DELETE_COST
            : -lengthDifference * INSERT_COST;
        if (minimumLengthCost > maxCost){
            return -1;
        }
        int cost = distance(found, given, maxCost);
        return cost <= maxCost ? 100 - cost : -1;
    }

    private static int substringScore(String found, String given){
        int pos = found.indexOf(given);
        if (pos < 0){
            return Integer.MIN_VALUE;
        }
        int rest = found.length() - (pos + given.length());
        return 100 - (rest * DELETE_COST) - (pos * DELETE_COST);
    }

    private static int distance(String found, String given, int maxCost){
        int[] previous = new int[given.length() + 1];
        int[] current = new int[given.length() + 1];
        for (int j = 0; j <= given.length(); j++){
            previous[j] = j * INSERT_COST;
        }

        for (int i = 1; i <= found.length(); i++){
            current[0] = i * DELETE_COST;
            int rowMinimum = current[0];
            for (int j = 1; j <= given.length(); j++){
                int substitutionCost = found.charAt(i - 1) == given.charAt(j - 1)
                    ? 0
                    : specialz(found.charAt(i - 1), given.charAt(j - 1))
                        ? SPECIAL_SUBSTITUTE_COST
                        : SUBSTITUTE_COST;
                int diagonal = previous[j - 1] + substitutionCost;
                int deletion = previous[j] + DELETE_COST;
                int insertion = current[j - 1] + INSERT_COST;
                current[j] = min(diagonal, deletion, insertion);
                rowMinimum = Math.min(rowMinimum, current[j]);
            }
            if (rowMinimum > maxCost){
                return maxCost == Integer.MAX_VALUE ? rowMinimum : maxCost + 1;
            }

            int[] temp = previous;
            previous = current;
            current = temp;
        }
        return previous[given.length()];
    }

    static int min( int a, int b, int c){
        if (b < a && b < c){
            return b;
        }
        if (c < a && c < b){
            return c;
        }
        return a;
    }

    static boolean specialz(char found, char given){
        if (
        ((found == 'a') && (given == '4')) ||
        ((found == '4') && (given == 'a')) ||
        ((found == '3') && (given == 'e')) ||
        ((found == 'e') && (given == '3')) ||
        ((found == '5') && (given == 's')) ||
        ((found == 's') && (given == '5')) ||
        ((found == 'o') && (given == '0')) ||
        ((found == '0') && (given == 'o'))
        )
        {
            return true;
        }
        return false;
    }
}

// last line