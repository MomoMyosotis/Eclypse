// first line

package app.helpers;

public class Levenshit {
    private static final int INSERT_COST = 4;
    private static final int DELETE_COST = 8;
    private static final int SUBSTITUTE_COST = 3;
    private static final int SPECIAL_SUBSTITUTE_COST = 1;

    private Levenshit(){}

        // how many operations do I need to do to change A into B?
    public static int levenstein(String found, String given){
        int score = 0;

        // in caso di match esattti ma preceduti o seguiti da altre cose es aaaadocumentoaaaa
        int pos = found.indexOf(given);
        if (pos >= 0){
            int rest = found.length() - (pos + given.length());
                score = 100-(rest * DELETE_COST) - (pos * DELETE_COST);
            return score;
        }
        int[][] matrix = new int[found.length() +1][given.length() +1];
        int costo = 0;
        // inizializzo colonna 0
        for (int i = 0; i <= found.length() ; i++){
            matrix[i][0] = i * DELETE_COST;
        }

        // inizializzo riga 0
        for (int j = 0; j <= given.length(); j++){
            matrix[0][j] = j * INSERT_COST;
        }

        for (int i = 1; i < found.length() +1; i++){
            for (int j = 1; j < given.length() +1; j++){
                int cd = 0;
                int cup = 0;
                int csx = 0;
                if (found.charAt(i -1) == given.charAt(j -1)){
                    cd += 0;
                }
                else if (specialz(found.charAt(i-1), given.charAt(j-1))){
                    cd += SPECIAL_SUBSTITUTE_COST;
                } else{
                    cd += SUBSTITUTE_COST;
                }
                cd = matrix[i -1][j-1] +cd;
                cup = matrix [i-1][j] + DELETE_COST;
                csx = matrix [i][j-1] + INSERT_COST;
                matrix [i][j] = min(cd, cup, csx);
            }
        }
        costo = matrix[found.length()][given.length()];
        score = 100 - costo;
        return score;
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