package jus.aor.nio.v3;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author morat
 */
public class ReadCont  extends Continuation{
    
    // Etats de l'automate utilisé pour la lecture
    private enum State {
        IDLE, READING
    };
    
    // Buffers pour lire la taille puis le message
    private ByteBuffer size_buf = ByteBuffer.allocate(4);
    private ByteBuffer message; // Alloué lorsque la taille est connue
    
    // Taille du message
    private int size;
    // Etat de l'automate
    private State state;
    
    
    /**
     * @param sc
     */
    public ReadCont(SocketChannel sc){
        super(sc);
        state = State.IDLE;
    }
    /**
     * Reads the message using an automata (first the size then the message)
     * @return the message
     * @throws IOException
     * @throws ClassNotFoundException
     */
    protected Message handleRead() throws IOException, ClassNotFoundException{
        Message msg = null;
        switch(state){
            case IDLE:
                readsize();
                break;
            case READING :
                msg = readmsg();
            default:
        }
        return msg;
    }
    
    /**
     * Reads the size (on 4 bytes) and initializes the buffer for the message
     * @throws IOException
     */
    private void readsize() throws IOException {
        socketChannel.read(size_buf);
        if(size_buf.remaining() == 0){
            state = State.READING;
            size = ReadCont.bytesToInt(size_buf);
            message = ByteBuffer.allocate(size);
        }
    }
    
    /**
     * Reads the message using the size
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private Message readmsg() throws IOException, ClassNotFoundException {
        socketChannel.read(message);
        if(message.remaining() == 0){
            state = State.IDLE;
            return new Message(message.array());
        }
        return null;
    }
    
}

