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
    // nombre de pas nécessaires à la lecture du message
    private int nsteps;
    
    
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
            case IDLE: // reading the message size
                readsize();
                break;
            case READING : // reading of the message
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
        if(size_buf.remaining() == 0){ // update automata state only if the message size is fully read
            state = State.READING;
            size = ReadCont.bytesToInt(size_buf);
            message = ByteBuffer.allocate(size); // creates the reading buffer of the right size
            nsteps = 0; // resets the number of steps for the lecture of one message
        }
    }
    
    /**
     * Reads the message using the size
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private Message readmsg() throws IOException, ClassNotFoundException {        
        socketChannel.read(message); // reading the received data in the reading buffer
        nsteps++; // increment steps to read the message
        if(message.remaining() == 0){ // if buffer is full (message is received)
            size_buf = ByteBuffer.allocate(4);
            state = State.IDLE; // changing automata state to IDLE : waiting for a new message size
            return new Message(message.array(), nsteps);
        }
        return null;
    }
    
}

