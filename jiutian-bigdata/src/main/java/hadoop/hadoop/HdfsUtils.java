package hadoop.hadoop;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.util.Progressable;
import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


public class HdfsUtils {
    public static String uri = "hdfs://10.3.6.7:9000/";

    /**
     * list files/directories/links names under a directory, not include embed
     * objects
     *
     * @param dir a folder path may like '/tmp/testdir'
     * @return List<String> list of file names
     * @throws IOException file io exception
     */
    public List<String> listAll(String dir) throws IOException, InterruptedException {
        if (StringUtils.isBlank(dir)) {
            return new ArrayList<String>();
        }
//        dir = uri + dir;
        Configuration conf = new Configuration();
//        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        FileSystem fs = FileSystem.get(URI.create(uri), conf, "hdfs");

        FileStatus[] stats = fs.listStatus(new Path(dir));
        List<String> names = new ArrayList<String>();
        for (int i = 0; i < stats.length; ++i) {
            if (stats[i].isFile()) {
                // regular file
                names.add(stats[i].getPath().toString());
            } else if (stats[i].isDirectory()) {
//                FileStatus[] stats1 = fs.listStatus(new Path(dir));
//                List<String> names1 = new ArrayList<String>();
//                Arrays.stream(stats1).collect(Collectors.toList()).forEach(e-> System.out.println(e));

                // dir
                names.add(stats[i].getPath().toString());
            } else if (stats[i].isSymlink()) {
                // is s symlink in linux
                names.add(stats[i].getPath().toString());
            }
        }

        fs.close();
        return names;
    }

    /**
     * make a new dir in the hdfs
     *
     * @param dir the dir may like '/tmp/testdir'
     * @return boolean true-success, false-failed
     * @throws IOException something wrong happends when operating files
     */
    public boolean mkdir(String dir) throws IOException {
        if (StringUtils.isBlank(dir)) {
            return false;
        }
        dir = uri + dir;
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dir), conf);
        if (!fs.exists(new Path(dir))) {
            fs.mkdirs(new Path(dir));
        }
        fs.close();
        return true;
    }

    /**
     * delete a dir in the hdfs.
     * if dir not exists, it will throw FileNotFoundException
     *
     * @param dir the dir may like '/tmp/testdir'
     * @return boolean true-success, false-failed
     * @throws IOException something wrong happends when operating files
     */
    public boolean deleteDir(String dir) throws IOException, InterruptedException {
        if (StringUtils.isBlank(dir)) {
            return false;
        }
        dir = uri + dir;
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dir), conf, "connect");

        fs.delete(new Path(dir), true);
        fs.close();
        return true;
    }

    /**
     * upload the local file to the hds,
     * notice that the path is full like /tmp/test.txt
     * if local file not exists, it will throw a FileNotFoundException
     *
     * @param localFile local file path, may like F:/test.txt or /usr/local/test.txt
     * @param hdfsFile  hdfs file path, may like /tmp/dir
     * @return boolean true-success, false-failed
     * @throws IOException file io exception
     */
    public boolean uploadLocalFile2HDFS(String localFile, String hdfsFile) throws IOException, InterruptedException {
        if (StringUtils.isBlank(localFile) || StringUtils.isBlank(hdfsFile)) {
            return false;
        }
        hdfsFile = uri + hdfsFile;
        Configuration config = new Configuration();
        FileSystem hdfs = FileSystem.get(URI.create(uri), config, "connect");
        Path src = new Path(localFile);
        Path dst = new Path(hdfsFile);
        hdfs.copyFromLocalFile(src, dst);
        hdfs.close();
        return true;
    }
    /**
     * create a new file in the hdfs.
     *
     * notice that the toCreateFilePath is the full path
     *
     * and write the content to the hdfs file.
     */

    /**
     * create a new file in the hdfs.
     * if dir not exists, it will create one
     *
     * @param newFile new file path, a full path name, may like '/tmp/test.txt'
     * @param content file content
     * @return boolean true-success, false-failed
     * @throws IOException file io exception
     */
    static FSDataOutputStream os;
    static FSDataOutputStream os1;

    public static boolean createNewHDFSFile(String newFile, String content) throws IOException, InterruptedException {
        if (StringUtils.isBlank(newFile) || null == content) {
            return false;
        }
        newFile = uri + newFile;
        Configuration config = new Configuration();
        FileSystem hdfs = FileSystem.get(URI.create(newFile), config, "hadoop");
//        FileSystem fs = FileSystem.get(URI.create(hdfsFile), conf,"connect");
        os = hdfs.create(new Path(newFile));
        os.write(content.getBytes("UTF-8"));
//        os.close();
//        FSDataOutputStream os1 = hdfs.append(new Path(newFile));
//        os1 = hdfs.append(new Path(newFile));
        os.write(content.getBytes("UTF-8"));
        os.write(content.getBytes("UTF-8"));
//        os.close();
//        hdfs.close();
        return true;
    }

    public void write(String content) {
        try {
            os.write(content.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * delete the hdfs file
     *
     * @param hdfsFile a full path name, may like '/tmp/test.txt'
     * @return boolean true-success, false-failed
     * @throws IOException file io exception
     */
    public boolean deleteHDFSFile(String hdfsFile) throws IOException {
        if (StringUtils.isBlank(hdfsFile)) {
            return false;
        }
        hdfsFile = uri + hdfsFile;
        Configuration config = new Configuration();
        FileSystem hdfs = FileSystem.get(URI.create(hdfsFile), config);
        Path path = new Path(hdfsFile);
        boolean isDeleted = hdfs.delete(path, true);
        hdfs.close();
        return isDeleted;
    }

    /**
     * read the hdfs file content
     *
     * @param hdfsFile a full path name, may like '/tmp/test.txt'
     * @return byte[] file content
     * @throws IOException file io exception
     */
    public byte[] readHDFSFile(String hdfsFile) throws Exception {
        if (StringUtils.isBlank(hdfsFile)) {
            return null;
        }
        hdfsFile = uri + hdfsFile;
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(hdfsFile), conf, "connect");
        // check if the file exists
        Path path = new Path(hdfsFile);
        if (fs.exists(path)) {
            FSDataInputStream is = fs.open(path);
            // get the file info to create the buffer
            FileStatus stat = fs.getFileStatus(path);
            // create the buffer
            byte[] buffer = new byte[Integer.parseInt(String.valueOf(stat.getLen()))];
            is.readFully(0, buffer);
            is.close();
            fs.close();
            return buffer;
        } else {
            throw new Exception("the file is not found .");
        }
    }

    /**
     * append something to file dst
     *
     * @param hdfsFile a full path name, may like '/tmp/test.txt'
     * @param content  string
     * @return boolean true-success, false-failed
     * @throws Exception something wrong
     */
    public boolean append(String hdfsFile, String content) throws Exception {
        if (StringUtils.isBlank(hdfsFile)) {
            return false;
        }
        if (StringUtils.isEmpty(content)) {
            return true;
        }

        hdfsFile = uri + hdfsFile;
        Configuration conf = new Configuration();
        // solve the problem when appending at single datanode hadoop env
        conf.set("dfs.client.block.write.replace-datanode-on-failure.policy", "NEVER");
        conf.set("dfs.client.block.write.replace-datanode-on-failure.enable", "true");
        FileSystem fs = FileSystem.get(URI.create(hdfsFile), conf);
        // check if the file exists
        Path path = new Path(hdfsFile);
        if (fs.exists(path)) {
            try {
                InputStream in = new ByteArrayInputStream(content.getBytes());
                OutputStream out = fs.append(new Path(hdfsFile));
                IOUtils.copyBytes(in, out, 4096, true);
                out.close();
                in.close();
                fs.close();
            } catch (Exception ex) {
                fs.close();
                throw ex;
            }
        } else {
            HdfsUtils.createNewHDFSFile(hdfsFile, content);
        }
        return true;
    }

}
