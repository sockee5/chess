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
        if (piece.getPieceType() == PieceType.KING) {
            return new KingMoveCalculator().pieceMoves(board,myPosition);
        }
        if (piece.getPieceType() == PieceType.KNIGHT) {
            return new KnightMoveCalculator().pieceMoves(board,myPosition);
        }
        if (piece.getPieceType() == PieceType.PAWN) {
            return new PawnMoveCalculator().pieceMoves(board,myPosition);
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

class KingMoveCalculator implements moveCalculator {
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

            if (x>0 && y>0 && x<=8 && y<=8 ) {

                ChessPosition position = new ChessPosition(y,x);
                if (board.getPiece(position) == null) {
                    moves.add(new ChessMove(myPosition, position, null));
                } else if (board.getPiece(position).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                    moves.add(new ChessMove(myPosition, position, null));
                }
            }
        }
        return moves;
    }
}

class KnightMoveCalculator implements moveCalculator {
    private final int[][] directions = {
            {2,1},{2,-1},{-2,1},{-2,-1},{1,2},{1,-2},{-1,2},{-1,-2}
    };

    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {

        Collection<ChessMove> moves = new ArrayList<>();

        for (int[] dir : directions) {
            int i = dir[0];
            int j = dir[1];

            int x = myPosition.getColumn() + i;
            int y = myPosition.getRow() + j;

            if (x>0 && y>0 && x<=8 && y<=8 ) {

                ChessPosition position = new ChessPosition(y,x);
                if (board.getPiece(position) == null) {
                    moves.add(new ChessMove(myPosition, position, null));
                } else if (board.getPiece(position).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                    moves.add(new ChessMove(myPosition, position, null));
                }
            }
        }
        return moves;
    }
}

class PawnMoveCalculator implements moveCalculator {

    public Collection<ChessMove> addMoves(ChessPosition myPosition, ChessPosition endPosition) {
        Collection<ChessMove> addedMoves = new ArrayList<>();
        if (endPosition.getRow() == 1 || endPosition.getRow() ==8) {
            addedMoves.add(new ChessMove(myPosition, endPosition, ChessPiece.PieceType.BISHOP));
            addedMoves.add(new ChessMove(myPosition, endPosition, ChessPiece.PieceType.ROOK));
            addedMoves.add(new ChessMove(myPosition, endPosition, ChessPiece.PieceType.QUEEN));
            addedMoves.add(new ChessMove(myPosition, endPosition, ChessPiece.PieceType.KNIGHT));
        } else {
            addedMoves.add(new ChessMove(myPosition, endPosition, null));
        }
        return addedMoves;
    }
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {

        Collection<ChessMove> moves = new ArrayList<>();
        ChessPiece myPiece = board.getPiece(myPosition);
        int j;
        int startRow = myPosition.getRow();

        if (myPiece.getTeamColor() == ChessGame.TeamColor.BLACK) {
            j = -1;
        } else {
            j = 1;
        }

        int x = myPosition.getColumn();
        int y = startRow + j;

        // Out of bounds
        if (y<1 || y>8 ) return moves;

        // Move one
        ChessPosition position = new ChessPosition(y,x);
        if (board.getPiece(position) == null) {
            moves.addAll(addMoves(myPosition,position));




            // pawn is in starting location
            if ((myPiece.getTeamColor() == ChessGame.TeamColor.BLACK && startRow == 7) || (myPiece.getTeamColor() == ChessGame.TeamColor.WHITE && startRow == 2)) {
                position = new ChessPosition(y+j,x);
                if (board.getPiece(position) == null) {
                    moves.addAll(addMoves(myPosition,position));
                }
            }
        }
        // Check for enemy pieces
        if (x-1>0) {
            position = new ChessPosition(y,x-1);
            if (board.getPiece(position) != null && board.getPiece(position).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                moves.addAll(addMoves(myPosition,position));
            }
        }
        if (x+1<9) {
            position = new ChessPosition(y,x+1);
            if (board.getPiece(position) != null && board.getPiece(position).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                moves.addAll(addMoves(myPosition,position));
            }
        }
    return moves;
    }
}