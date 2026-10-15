package chess.movecalculators;

import chess.*;

import java.util.ArrayList;
import java.util.Collection;

public class PawnMoveCalculator {

    private Collection<ChessMove> checkPromotion(ChessBoard board, ChessPosition myPosition, int newRow, int newCol) {
        Collection<ChessMove> promotionMoves = new ArrayList<>();
        ChessPosition newPos = new ChessPosition(newRow,newCol);
        if (newRow==8 || newRow==1) {
            promotionMoves.add(new ChessMove(myPosition, newPos, ChessPiece.PieceType.KNIGHT));
            promotionMoves.add(new ChessMove(myPosition, newPos, ChessPiece.PieceType.ROOK));
            promotionMoves.add(new ChessMove(myPosition, newPos, ChessPiece.PieceType.BISHOP));
            promotionMoves.add(new ChessMove(myPosition, newPos, ChessPiece.PieceType.QUEEN));
        } else {
            promotionMoves.add(new ChessMove(myPosition, newPos, null));
        }

        return promotionMoves;
    }

    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();
        int stepCounter;
        ChessPiece checkPiece;

        int startRow = myPosition.getRow();
        int startCol = myPosition.getColumn();
        ChessPiece myPiece = board.getPiece(myPosition);
        ChessGame.TeamColor myColor = myPiece.getTeamColor();

        if (myColor == ChessGame.TeamColor.WHITE) {
            stepCounter = 1;
        } else {
            stepCounter = -1;
        }

        if (startRow+stepCounter>0 && startRow+stepCounter<9) {
            if (board.getPiece(new ChessPosition(startRow+stepCounter,startCol)) == null){
                moves.addAll(checkPromotion(board, myPosition, startRow + stepCounter, startCol));
                if (((myColor == ChessGame.TeamColor.WHITE && startRow==2) || (myColor == ChessGame.TeamColor.BLACK && startRow==7))
                        && board.getPiece(new ChessPosition(startRow+stepCounter*2,startCol)) == null) {
                    moves.addAll(checkPromotion(board,myPosition,startRow+stepCounter*2, startCol));
                }
            }


            if (startCol-1>0) {
                checkPiece = board.getPiece(new ChessPosition(startRow+stepCounter,startCol-1));
                if (checkPiece != null && checkPiece.getTeamColor() != myColor) {
                    moves.addAll(checkPromotion(board, myPosition, startRow + stepCounter, startCol - 1));
                }
            }
            if (startCol+1<9) {
                checkPiece = board.getPiece(new ChessPosition(startRow+stepCounter,startCol+1));
                if (checkPiece != null && checkPiece.getTeamColor() != myColor) {
                    moves.addAll(checkPromotion(board, myPosition, startRow + stepCounter, startCol + 1));
                }
            }
        }

        return moves;
    }

}
