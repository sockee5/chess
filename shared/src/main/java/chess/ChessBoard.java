package chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    private ChessPiece[][] board = new ChessPiece[8][8];
    private ChessPosition whiteKingPosition;
    private ChessPosition blackKingPosition;

    public ChessBoard() {

    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        this.board[position.getRow()-1][position.getColumn()-1] = piece;
        if (piece==null) {
            return;
        }
        if (piece.getPieceType() == ChessPiece.PieceType.KING) {
            if (piece.getTeamColor()== ChessGame.TeamColor.WHITE) {
                whiteKingPosition = position;
            } else {
                blackKingPosition = position;
            }
        }
    }

    public void movePiece(ChessMove move) {
        ChessPosition startPosition = move.getStartPosition();
        ChessPosition endPosition = move.getEndPosition();
        ChessPiece piece = getPiece(startPosition);

        this.board[startPosition.getRow()-1][startPosition.getColumn()-1] = null;
        if (move.getPromotionPiece()!=null) { // if promotion piece is not null, set new location to new piece
            piece = new ChessPiece(piece.getTeamColor(), move.getPromotionPiece());
        }
        this.addPiece(endPosition, piece);
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return board[position.getRow()-1][position.getColumn()-1];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        this.board = new ChessPiece[8][8];

        // White
        addPiece(new ChessPosition(1,1), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));
        addPiece(new ChessPosition(1,2), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPosition(1,3), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPosition(1,4), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN));
        addPiece(new ChessPosition(1,5), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING));
        addPiece(new ChessPosition(1,6), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPosition(1,7), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPosition(1,8), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));

        for (int x=1; x<=8; x++) {
            addPiece(new ChessPosition(2, x), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN));
        }

        addPiece(new ChessPosition(8,1), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK));
        addPiece(new ChessPosition(8,2), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPosition(8,3), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPosition(8,4), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN));
        addPiece(new ChessPosition(8,5), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING));
        addPiece(new ChessPosition(8,6), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPosition(8,7), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPosition(8,8), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK));

        for (int x=1; x<=8; x++) {
            addPiece(new ChessPosition(7, x), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));
        }
    }

    public void findKingPositions() {
        for (int row=0; row<8; row++) {
            for (int col=0; col<8; col++) {
                if (board[row][col]!=null) {
                    chess.ChessPiece piece = board[row][col];
                    if (piece.getPieceType()== ChessPiece.PieceType.KING) {
                        ChessPosition position = new ChessPosition(row+1,col+1);
                        if (piece.getTeamColor()== ChessGame.TeamColor.WHITE) {
                            whiteKingPosition = position;
                        } else {
                            blackKingPosition = position;
                        }
                    }
                }
            }
        }
    }

    public ChessPosition getKingPosition(ChessGame.TeamColor color) {
        if (color== ChessGame.TeamColor.WHITE) {
            return whiteKingPosition;
        } else {
            return blackKingPosition;
        }
    }

    public Collection<ChessMove> teamMoves(ChessGame.TeamColor color) {
        Collection<ChessMove> totalMoves = new ArrayList<>();
        Collection<ChessPosition> allTeamPositions = teamPositions(color);
        for (ChessPosition position : allTeamPositions) {
            ChessPiece piece = getPiece(position);
            if (piece.getTeamColor()==color) {
                totalMoves.addAll(piece.pieceMoves(this, position));
            }
        }

        return totalMoves;
    }

    public Collection<ChessPosition> teamPositions(ChessGame.TeamColor color) {
        Collection<ChessPosition> allTeamPositions = new ArrayList<>();
        for (int row=0; row<8; row++) {
            for (int col=0; col<8; col++) {
                ChessPiece piece = board[row][col];
                if (piece!=null && piece.getTeamColor()==color) {
                    allTeamPositions.add(new ChessPosition(row+1,col+1));
                }
            }
        }
        return allTeamPositions;
    }

    public ChessBoard(ChessBoard other) {
        this.board = new ChessPiece[8][8];
        for (int row=0; row<8; row++) {
            for (int col=0; col<8; col++) {
                ChessPiece piece = other.board[row][col];
                if (piece!=null) {
                    this.board[row][col] = new ChessPiece(piece.getTeamColor(),piece.getPieceType());
                }
            }
        }
            this.findKingPositions();
    }



    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ChessBoard that)) {
            return false;
        }
        return Objects.deepEquals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }
}
