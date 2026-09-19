package chess;

import java.util.*;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    private final ChessGame.TeamColor pieceColor;
    private final ChessPiece.PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ChessPiece piece = board.getPiece(myPosition);
        if (piece.getPieceType() == PieceType.ROOK) {
            return new RookMoveCalculator().pieceMoves(board,myPosition);
        }
        if (piece.getPieceType() == PieceType.BISHOP) {
            return new BishopMoveCalculator().pieceMoves(board,myPosition);
        }
        if (piece.getPieceType() == PieceType.QUEEN) {
            return new QueenMoveCalculator().pieceMoves(board,myPosition);
        }

        return List.of();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ChessPiece that)) {
            return false;
        }
        return pieceColor == that.pieceColor && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type);
    }
}

interface moveCalculator {
        Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition);
}

class BishopMoveCalculator implements moveCalculator {
    private final int[][] directions = {
            {1,1},{1,-1},{-1,1},{-1,-1}
    };

    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {

        Collection<ChessMove> moves = new ArrayList<>();

        for (int[] dir : directions) {
            int i = dir[0];
            int j = dir[1];

            int x = myPosition.getColumn() + i;
            int y = myPosition.getRow() + j;

            while (x>0 && y>0 && x<=8 && y<=8 ) {

                ChessPosition position = new ChessPosition(y,x);

                if (board.getPiece(position) == null) {
                    moves.add(new ChessMove(myPosition, position, null));
                } else if (board.getPiece(position).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                    moves.add(new ChessMove(myPosition, position, null));
                    break;
                } else {
                    break;
                }
                x +=i;
                y+=j;
            }
        }
        return moves;
    }
}


class RookMoveCalculator implements moveCalculator {
    private final int[][] directions = {
            {1,0},{-1,0},{0,1},{0,-1}
    };


    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {

        Collection<ChessMove> moves = new ArrayList<>();

        for (int[] dir : directions) {
            int i = dir[0];
            int j = dir[1];

            int x = myPosition.getColumn() + i;
            int y = myPosition.getRow() + j;

            while (x>0 && y>0 && x<=8 && y<=8 ) {

                ChessPosition position = new ChessPosition(y,x);
                if (board.getPiece(position) == null) {
                    moves.add(new ChessMove(myPosition, position, null));
                } else if (board.getPiece(position).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                    moves.add(new ChessMove(myPosition, position, null));
                    break;
                } else {
                    break;
                }
                x +=i;
                y+=j;
            }
        }
        return moves;
    }
}

class QueenMoveCalculator implements moveCalculator {
    private final int[][] directions = {
            {1,0},{-1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}
    };


    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {

        Collection<ChessMove> moves = new ArrayList<>();

        for (int[] dir : directions) {
            int i = dir[0];
            int j = dir[1];

            int x = myPosition.getColumn() + i;
            int y = myPosition.getRow() + j;

            while (x>0 && y>0 && x<=8 && y<=8 ) {

                ChessPosition position = new ChessPosition(y,x);
                if (board.getPiece(position) == null) {
                    moves.add(new ChessMove(myPosition, position, null));
                } else if (board.getPiece(position).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                    moves.add(new ChessMove(myPosition, position, null));
                    break;
                } else {
                    break;
                }
                x +=i;
                y+=j;
            }
        }
        return moves;
    }
}