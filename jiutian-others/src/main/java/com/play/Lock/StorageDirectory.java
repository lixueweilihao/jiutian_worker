package com.play.Lock;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.OverlappingFileLockException;

/**
 * Copyright @ 2018 lihao.com
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/9/30  15:28
 */
public class StorageDirectory {
    public static final String STORAGE_FILE_LOCK = "in_use.lock" ;
    File root ; //root directory
    java.nio.channels.FileLock lock ; //storage lock
    /**
     * Lock storage to provide exclusive access.
     *
     * <p> if locking is supported we guarantee exculsive access to the
     * storage directory . Otherwise, no guarantee is given
     *
     * @throws Exception if locking file
     */
    public StorageDirectory(File dir){
        this.root = dir ;
    }
    public void lock() throws Exception{
        this.lock = tryLock() ;
        if(lock == null){
            String msg = "Cannot lock storage" + this.root + ". The directory is already locked." ;
            System.err.println(msg) ;
            throw new IOException(msg) ;
        }
    }
    public void unlock() throws IOException{
        if(this.lock == null)
            return ;
        this.lock.release() ;
        lock.channel().close() ;
        lock = null ;
    }
    /**
     * Attempts to acquire an exclusive lock on the storage
     * @return  A lock object representing the newly-acquired lock or null if
     * storage is already lockd .
     * @throws IOException
     */
    java.nio.channels.FileLock tryLock() throws IOException{
        boolean deletionHookAdded = false ;
        File lockF = new File(root,STORAGE_FILE_LOCK) ;

        //如果没有文件
        if(!lockF.exists()){
            lockF.deleteOnExit() ;
            deletionHookAdded = true ;
        }

        RandomAccessFile file = new RandomAccessFile(lockF,"rws") ;
        java.nio.channels.FileLock res = null ;
        try{
            res = file.getChannel().tryLock() ;
        }catch(OverlappingFileLockException oe){
            file.close() ;
            return null ;
        }catch(IOException e){
            System.err.println("Cannot create lock on " + lockF) ;
            file.close() ;
            throw e ;
        }
        if(res !=null && !deletionHookAdded){
            //if the file existed prior to our startup, we didn't
            //call deleteOnExit above. But since we successfully locked
            //the dir , we can take care of cleaning it up
            lockF.deleteOnExit() ;
        }
        return res ;
    }

}



