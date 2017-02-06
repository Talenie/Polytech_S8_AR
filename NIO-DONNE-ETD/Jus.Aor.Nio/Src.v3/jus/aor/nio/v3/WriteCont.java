package jus.aor.nio.v3;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * @author morat
 */
public class WriteCont extends Continuation{
    
    private SelectionKey key;
    // state automata
    private enum State{WRITING_DONE,WRITING_LENGTH,WRITING_DATA;}
    // initial state
    protected State state = State.WRITING_DONE;
    // the list of bytes messages to write
    protected ArrayList<byte[]> msgs = new ArrayList<>() ;
    // buf contains the byte array that is currently written
    protected ByteBuffer buf = null;
    
    //Buffer containing the size
    private ByteBuffer size_buf;
       
    
    /**
     * @param k
     * @param sc
     */
    public WriteCont(SelectionKey k,SocketChannel sc){
        super(sc);
        key = k;
    }
    
    
    /**
     * @return true if the msgs are not completly write.
     */
    protected boolean isPendingMsg(){
        return !msgs.isEmpty();
    }
    
    
    /**
     * @param data
     * @throws IOException
     */
    protected void sendMsg(Message data) throws IOException{
        msgs.add(data.marshall());
        key.interestOps(SelectionKey.OP_WRITE|SelectionKey.OP_READ);
    }
    
    
    /**
     * @throws IOException
     */
    protected void handleWrite()throws IOException{
        switch(state){
            case WRITING_DONE :
                if(isPendingMsg()){
                    buf = ByteBuffer.wrap(msgs.remove(0));
                    state = State.WRITING_LENGTH;
                }
                break;
            case WRITING_DATA :
                writedata();
                break;
            case WRITING_LENGTH :
                writelength();
                
            default :
        }
    }
    
    
    private void writelength() throws IOException {
        size_buf = Continuation.intToBytes(buf.remaining());
        socketChannel.write(size_buf);
        if(size_buf.remaining() == 0){
            state = State.WRITING_DATA;
        }
    }
    
    private void writedata() throws IOException {
        socketChannel.write(buf);
        if(buf.remaining() == 0){
            state = State.WRITING_DONE;
            if(!isPendingMsg()) key.interestOps(SelectionKey.OP_READ);
        }
    }
}
