package execfile;


import java.io.File;
import java.io.IOException;

public class IOOperation {
    //创建文件
    public void CreateFile(File f){
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(File.separator);
        System.out.println(File.pathSeparator);
    }
    //删除文件
    public void DeleteFile(File f){
        if(f.exists()){
            f.delete();
        }else {
            System.out.println("文件不存在!");
        }
    }
    //创建文件夹
    public void CreateMkdir(String name){
        File f = new File(name);
        f.mkdir();
    }
    public void DeletdAll(File file){
    if(file.isDirectory()){
        File[] fs = file.listFiles();
        for(int i=0;i<fs.length;i++){
            if(fs[i].isDirectory()){
                DeleteFile(fs[i]);
            }else {
                fs[i].delete();
            }
        }
    }else {
        file.delete();
    }
    }
    //列出文件夹下所有文件
    public void GetAllFile(File f){
        File[] str = f.listFiles();
        for(File strs:str){
            System.out.println(strs);
        }
    }
    //判断指定路径是否为目录
    public void GetIfDir(String fileName){
        File f = new File(fileName);
        if (f.isDirectory()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    //搜索指定目录的全部内容
    private void Print(File f) {
        if (f != null) {
            if (f.isDirectory()) {
                File[] fileArr = f.listFiles();
                if (fileArr != null) {
                    for (File file : fileArr) {
                        Print(file);
                    }
                }
            } else {
                System.out.println(f);
            }
        }
    }

    public static void main(String[] args) {
        IOOperation io = new IOOperation();
        File f = new File("D:"+File.separator+"hello");
//        io.DeleteFile(f);
//        f.mkdirs();
        io.DeletdAll(f);
    }
}