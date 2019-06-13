package execfile;


import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileOutputStreamDemo {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = null;
        FileChannel fc = null;
        long pos;
        byte b[] = {65, 66, 67, 68, 69};

        try{
            // create new file output stream
            fos=new FileOutputStream("C://test.txt");

            // write buffer to the output stream
            fos.write(b);

            // pushes stream content to the underlying file
            fos.flush();

            // returns file channel associated with this stream
            fc = fos.getChannel();
            System.out.println(fc);
            System.out.println(fc.size());

            // returns the number of bytes written
            pos = fc.position();

            // prints
            System.out.print(pos);

        }catch(Exception ex){
            // if an error occurs
            ex.printStackTrace();
        }finally{
            if(fos!=null)
                fos.close();
            if(fc!=null)
                fc.close();
        }
    }
}
