package chess.movecalculators;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.*;

public class RookMoveCalculator implements MoveCalculator{
    private final int[][] directions = {
            {1,0},{-1,0},{0,1},{0,-1}
    };

    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<ChessMove>();
        int startRow = myPosition.getRow();
        int startCol = myPosition.getColumn();

        for (int[] dir : directions) {
            int i = dir[0];
            int j = dir[1];

            int y = startRow + i;
            int x = startCol + j;

            while (x>0 && y>0 && x<9 && y<9) {
                chess.ChessPosition newPosition = new ChessPosition(y,x);
                if (board.getPiece(newPosition) == null) {
                    moves.add(new ChessMove(myPosition, newPosition, null));
                } else if (board.getPiece(newPosition).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                    moves.add(new ChessMove(myPosition, newPosition, null));
                    break;
                } else {
                    break;
                }
                y+=i;
                x+=j;
            }
        }

        return moves;
    }
}
