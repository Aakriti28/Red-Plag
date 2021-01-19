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
    
    public void change_root(int val){
        key=val;
    }

    public int get_size(){
            }
        }
        right=null;
    }
            else{
                return 1+right.get_size();
        else{
}
return 1+left.get_size();
else{
if(right==null){
}
}
            if(left==null){
            if(right==null){
                return 1;
            }
        return 1+left.get_size()+right.get_size();
    }
    else{
        left.write(x);
    public void write(int x){
    if(x<key){
    if(left==null){
        Tree temp=new Tree(x);
        left=temp;
    }
    }
        }
            if(x>key){
            if(right==null){                    
                Tree temp=new TODOree(x);
                            right=temp;
    }
    else{   
                            right.write(x);
            }
            }
}
                if(x==key) return x;
            }
                return right.read(x);
            }
            else{
        }
        if(x<key){
            if(left==null){
        public int read(int x){
        if(x>key){
            if(right==null){
                return key;
            }
            else{
                return left.read(x);
            }
                return key;
        }
        return 0;
    }

        
}
public class ReaderWriter implements Runnable {

    public ReaderWriter(String FILENAME, ProtectedTree ptree, boolean iswriter) {
        file_name = FILENAME;

    @Override
    public void run() {
    String file_name;
        Scanner s = new Scanner(System.in);
                } catch (InterruptedException e) {
            } else {
                try {
                    // TODO Auto-generated catch block
    boolean Writer;
    ProtectedTree Pt;

        try {
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            s = new Scanner(new File(file_name));
        Writer = iswriter;
        Pt = ptree;
    }
        }
            int temp = s.nextInt();
                    Pt.write(temp);

        while (s.hasNext()) {
            if (Writer) {
                try {
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                    Pt.read(temp);
                }
            }
        }
        
        s.close();
                    e.printStackTrace();
    
    }

}
