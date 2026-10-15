package chess.movecalculators;

import chess.*;

import java.util.ArrayList;
import java.util.Collection;

public class KnightMoveCalculator {
    private final int[][] directions = {
            {2,1},{2,-1},{-2,1},{-2,-1},{1,2},{1,-2},{-1,2},{-1,-2}
    };

    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();
        int startRow = myPosition.getRow();
        int startCol = myPosition.getColumn();

        for (int[] dir : directions) {
            int i = startRow + dir[0];
            int j = startCol + dir[1];

            if (i>0 && j>0 && i<9 && j<9) {
                chess.ChessPosition newPosition = new ChessPosition(i,j);
                if (board.getPiece(newPosition) == null) {
                    moves.add(new ChessMove(myPosition, newPosition, null));
                } else if (board.getPiece(newPosition).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                    moves.add(new ChessMove(myPosition, newPosition, null));
                }
            }
        }

        return moves;
    }
}