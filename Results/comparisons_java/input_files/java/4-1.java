import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
package readwrite;
public class Tree{
    int key;
    Tree left,right;
    public Tree(int val){
        key=val;
        left=null;
        right=null;
    }
    public Tree(){
        key=0;
        left=null;
        right=null;
    }
    
    public void change_root(int val){
        key=val;
    }

    public int get_size(){
        if(left==null){
            if(right==null){
                return 1;
            }
            else{
                return 1+right.get_size();
            }
        }
        else{
            if(right==null){
                return 1+left.get_size();
            }
            else{
                return 1+left.get_size()+right.get_size();
            }
        }
    }
    public void write(int x){
        if(x<key){
            if(left==null){
                Tree temp=new Tree(x);
                left=temp;
            }
            else{
                left.write(x);
            }
        }
        if(x>key){
            if(right==null){
                Tree temp=new Tree(x);
                right=temp;
            }
            else{
                right.write(x);
            }
        }
    }
    public int read(int x){
        if(x==key) return x;
        if(x>key){
            if(right==null){
                return key;
            }
            else{
                return right.read(x);
            }
        }
        if(x<key){
            if(left==null){
                return key;
            }
            else{
                return left.read(x);
            }
        }
        return 0;
    }

        
}
public class ReaderWriter implements Runnable {
    String file_name;
    boolean Writer;
    ProtectedTree Pt;

    public ReaderWriter(String FILENAME, ProtectedTree ptree, boolean iswriter) {
        file_name = FILENAME;
        Writer = iswriter;
        Pt = ptree;
    }

    @Override
    public void run() {

        Scanner s = new Scanner(System.in);
        try {
            s = new Scanner(new File(file_name));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        while (s.hasNext()) {
            int temp = s.nextInt();
            if (Writer) {
                try {
                    Pt.write(temp);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                try {
                    Pt.read(temp);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        
        s.close();
    
    }

}
